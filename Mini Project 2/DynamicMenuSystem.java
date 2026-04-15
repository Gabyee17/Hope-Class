package Mini_Project_2;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

enum Category {
    STARTER, MAIN_COURSE, DESSERT, BEVERAGE, SPECIAL
}

enum OrderStatus {
    PLACED, PREPARING, READY, SERVED, CANCELLED
}

interface DiscountStrategy {
    double applyDiscount(double price);
    String getDiscountName();
}

class NoDiscount implements DiscountStrategy {
    public double applyDiscount(double price) { return price; }
    public String getDiscountName()           { return "No Discount"; }
}

class PercentageDiscount implements DiscountStrategy {
    private double percent;
    public PercentageDiscount(double percent) {
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("Percent must be 0-100.");
        this.percent = percent;
    }
    public double applyDiscount(double price) { return price - (price * percent / 100); }
    public String getDiscountName()           { return percent + "% Off"; }
}

class FlatDiscount implements DiscountStrategy {
    private double flatAmount;
    public FlatDiscount(double flatAmount) {
        if (flatAmount < 0) throw new IllegalArgumentException("Flat discount cannot be negative.");
        this.flatAmount = flatAmount;
    }
    public double applyDiscount(double price) { return Math.max(0, price - flatAmount); }
    public String getDiscountName()           { return "Flat Rs." + flatAmount + " Off"; }
}

class HappyHourDiscount implements DiscountStrategy {
    public double applyDiscount(double price) { return price * 0.5; }
    public String getDiscountName()           { return "Happy Hour (50% Off)"; }
}

abstract class MenuItem {
    private String   itemId;
    private String   name;
    private double   basePrice;
    private Category category;
    private boolean  isAvailable;
    private String   description;

    public MenuItem(String itemId, String name, double basePrice, Category category, String description) {
        if (name == null || name.isEmpty())               throw new IllegalArgumentException("Item name cannot be empty.");
        if (basePrice < 0)                                throw new IllegalArgumentException("Price cannot be negative.");
        if (description == null || description.isEmpty()) throw new IllegalArgumentException("Description cannot be empty.");
        this.itemId      = itemId;
        this.name        = name;
        this.basePrice   = basePrice;
        this.category    = category;
        this.description = description;
        this.isAvailable = true;
    }

    public String   getItemId()      { return itemId; }
    public String   getName()        { return name; }
    public double   getBasePrice()   { return basePrice; }
    public Category getCategory()    { return category; }
    public boolean  isAvailable()    { return isAvailable; }
    public String   getDescription() { return description; }

    public void setBasePrice(double basePrice) {
        if (basePrice < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.basePrice = basePrice;
    }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name;
    }

    public abstract double getFinalPrice(DiscountStrategy discount);
    public abstract String getItemType();

    public String toString() {
        return String.format("[%s] %s (ID: %s) | Category: %s | Base Price: Rs.%.2f | Available: %s",
                getItemType(), name, itemId, category, basePrice, isAvailable ? "Yes" : "No");
    }
}

class FoodItem extends MenuItem {
    private boolean isVegetarian;

    public FoodItem(String itemId, String name, double basePrice, Category category,
                    String description, boolean isVegetarian) {
        super(itemId, name, basePrice, category, description);
        this.isVegetarian = isVegetarian;
    }

    public boolean isVegetarian() { return isVegetarian; }
    public String  getItemType()  { return isVegetarian ? "VEG" : "NON-VEG"; }

    public double getFinalPrice(DiscountStrategy discount) {
        return Math.round(discount.applyDiscount(getBasePrice()) * 100.0) / 100.0;
    }

    public String toString() {
        return super.toString() + String.format(" | %s | %s",
                isVegetarian ? "[VEG]" : "[NON-VEG]", getDescription());
    }
}

class ComboItem extends MenuItem {
    private List<MenuItem> comboItems;
    private double         comboDiscount;

    public ComboItem(String itemId, String name, Category category, String description, double comboDiscount) {
        super(itemId, name, 0, category, description);
        if (comboDiscount < 0 || comboDiscount > 100) throw new IllegalArgumentException("Combo discount must be 0-100.");
        this.comboItems    = new ArrayList<>();
        this.comboDiscount = comboDiscount;
    }

    public void addToCombo(MenuItem item) {
        if (item == null) throw new IllegalArgumentException("Cannot add null item to combo.");
        comboItems.add(item);
        double total = comboItems.stream().mapToDouble(MenuItem::getBasePrice).sum();
        setBasePrice(total);
    }

    public String getItemType() { return "COMBO"; }

    public double getFinalPrice(DiscountStrategy discount) {
        double afterCombo    = getBasePrice() * (1 - comboDiscount / 100);
        double afterDiscount = discount.applyDiscount(afterCombo);
        return Math.round(afterDiscount * 100.0) / 100.0;
    }

    public String toString() {
        return super.toString() + String.format(" | Combo Discount: %.0f%% | Items: %d", comboDiscount, comboItems.size());
    }
}

class SpecialItem extends MenuItem {
    private String availableUntil;
    private int    limitedQuantity;
    private int    quantitySold;

    public SpecialItem(String itemId, String name, double basePrice, String description,
                       String availableUntil, int limitedQuantity) {
        super(itemId, name, basePrice, Category.SPECIAL, description);
        if (limitedQuantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        this.availableUntil  = availableUntil;
        this.limitedQuantity = limitedQuantity;
        this.quantitySold    = 0;
    }

    public String  getItemType()          { return "SPECIAL"; }
    public boolean isStillAvailable()     { return (limitedQuantity - quantitySold) > 0; }
    public int     getRemainingQuantity() { return limitedQuantity - quantitySold; }

    public void sellOne() {
        if (!isStillAvailable()) throw new IllegalStateException("Item '" + getName() + "' is sold out!");
        quantitySold++;
        if (!isStillAvailable()) setAvailable(false);
    }

    public double getFinalPrice(DiscountStrategy discount) {
        return Math.round(discount.applyDiscount(getBasePrice()) * 100.0) / 100.0;
    }

    public String toString() {
        return super.toString() + String.format(" | Available Until: %s | Remaining: %d",
                availableUntil, getRemainingQuantity());
    }
}

class OrderItem {
    private MenuItem         menuItem;
    private int              quantity;
    private DiscountStrategy discount;

    public OrderItem(MenuItem menuItem, int quantity, DiscountStrategy discount) {
        if (menuItem == null)        throw new IllegalArgumentException("Menu item cannot be null.");
        if (quantity <= 0)           throw new IllegalArgumentException("Quantity must be at least 1.");
        if (!menuItem.isAvailable()) throw new IllegalStateException(menuItem.getName() + " is not available.");
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.discount = discount;
    }

    public MenuItem getMenuItem() { return menuItem; }
    public int      getQuantity() { return quantity; }
    public double   getSubtotal() { return menuItem.getFinalPrice(discount) * quantity; }

    public String toString() {
        return String.format("  %-25s x%d  Rs.%.2f each  Subtotal: Rs.%.2f  [%s]",
                menuItem.getName(), quantity, menuItem.getFinalPrice(discount),
                getSubtotal(), discount.getDiscountName());
    }
}

class Order {
    private static int counter = 100;

    private String          orderId;
    private String          customerName;
    private String          tableNumber;
    private List<OrderItem> items;
    private OrderStatus     status;
    private LocalDateTime   placedAt;
    private double          taxRate;

    public Order(String customerName, String tableNumber, double taxRate) {
        if (customerName == null || customerName.isEmpty()) throw new IllegalArgumentException("Customer name required.");
        if (tableNumber == null  || tableNumber.isEmpty())  throw new IllegalArgumentException("Table number required.");
        if (taxRate < 0)                                    throw new IllegalArgumentException("Tax rate cannot be negative.");
        this.orderId      = "ORD" + (++counter);
        this.customerName = customerName;
        this.tableNumber  = tableNumber;
        this.taxRate      = taxRate;
        this.items        = new ArrayList<>();
        this.status       = OrderStatus.PLACED;
        this.placedAt     = LocalDateTime.now();
    }

    public String      getOrderId()      { return orderId; }
    public String      getCustomerName() { return customerName; }
    public OrderStatus getStatus()       { return status; }
    public void        setStatus(OrderStatus status) { this.status = status; }

    public void addItem(OrderItem item) {
        if (status != OrderStatus.PLACED)
            throw new IllegalStateException("Cannot add items to an order that is already " + status);
        items.add(item);
        System.out.println("Added: " + item.getMenuItem().getName() + " x" + item.getQuantity());
    }

    public void removeItem(String itemId) {
        boolean removed = items.removeIf(oi -> oi.getMenuItem().getItemId().equals(itemId));
        if (!removed) throw new IllegalArgumentException("Item ID " + itemId + " not found in order.");
        System.out.println("Removed item " + itemId + " from order " + orderId);
    }

    public double getSubtotal()  { return items.stream().mapToDouble(OrderItem::getSubtotal).sum(); }
    public double getTaxAmount() { return Math.round(getSubtotal() * taxRate / 100 * 100.0) / 100.0; }
    public double getTotal()     { return Math.round((getSubtotal() + getTaxAmount()) * 100.0) / 100.0; }

    public void cancelOrder() {
        if (status == OrderStatus.SERVED || status == OrderStatus.CANCELLED)
            throw new IllegalStateException("Cannot cancel an order that is " + status);
        status = OrderStatus.CANCELLED;
        System.out.println("Order " + orderId + " cancelled.");
    }

    public void printBill() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("==========================================");
        System.out.println("         DYNAMIC MENU SYSTEM             ");
        System.out.println("==========================================");
        System.out.printf ("  Order ID   : %s%n", orderId);
        System.out.printf ("  Customer   : %s%n", customerName);
        System.out.printf ("  Table      : %s%n", tableNumber);
        System.out.printf ("  Placed At  : %s%n", placedAt.format(fmt));
        System.out.printf ("  Status     : %s%n", status);
        System.out.println("==========================================");
        System.out.println("  ITEMS ORDERED:");
        for (OrderItem oi : items) System.out.println(oi);
        System.out.println("==========================================");
        System.out.printf ("  Subtotal   : Rs.%.2f%n", getSubtotal());
        System.out.printf ("  Tax (%.0f%%)  : Rs.%.2f%n", taxRate, getTaxAmount());
        System.out.printf ("  TOTAL      : Rs.%.2f%n", getTotal());
        System.out.println("==========================================");
    }

    public String toString() {
        return String.format("Order[%s | Customer: %s | Table: %s | Items: %d | Total: Rs.%.2f | Status: %s]",
                orderId, customerName, tableNumber, items.size(), getTotal(), status);
    }
}

class Menu {
    private String         restaurantName;
    private List<MenuItem> items;

    public Menu(String restaurantName) {
        if (restaurantName == null || restaurantName.isEmpty())
            throw new IllegalArgumentException("Restaurant name cannot be empty.");
        this.restaurantName = restaurantName;
        this.items          = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        if (item == null) throw new IllegalArgumentException("Cannot add null item.");
        boolean exists = items.stream().anyMatch(i -> i.getItemId().equals(item.getItemId()));
        if (exists) throw new IllegalArgumentException("Item with ID " + item.getItemId() + " already exists.");
        items.add(item);
        System.out.println("Menu item added: " + item.getName());
    }

    public void removeItem(String itemId) {
        boolean removed = items.removeIf(i -> i.getItemId().equals(itemId));
        if (!removed) throw new IllegalArgumentException("No item found with ID: " + itemId);
        System.out.println("Item " + itemId + " removed from menu.");
    }

    public MenuItem findById(String itemId) {
        return items.stream()
                .filter(i -> i.getItemId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + itemId));
    }

    public List<MenuItem> getByCategory(Category category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : items)
            if (item.getCategory() == category) result.add(item);
        return result;
    }

    public List<MenuItem> getAvailableItems() {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : items)
            if (item.isAvailable()) result.add(item);
        return result;
    }

    public List<MenuItem> searchByName(String keyword) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : items)
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) result.add(item);
        return result;
    }

    public void displayMenu(DiscountStrategy discount) {
        System.out.println("\n==========================================");
        System.out.println("  MENU - " + restaurantName);
        System.out.println("==========================================");
        for (Category cat : Category.values()) {
            List<MenuItem> catItems = getByCategory(cat);
            if (catItems.isEmpty()) continue;
            System.out.println("\n  -- " + cat + " --");
            for (MenuItem item : catItems) {
                if (!item.isAvailable()) continue;
                System.out.printf("  [%-4s] %-22s Base: Rs.%-8.2f Final: Rs.%.2f  [%s]%n",
                        item.getItemId(), item.getName(),
                        item.getBasePrice(), item.getFinalPrice(discount),
                        discount.getDiscountName());
            }
        }
        System.out.println();
    }

    public void updateItemPrice(String itemId, double newPrice) {
        MenuItem item = findById(itemId);
        item.setBasePrice(newPrice);
        System.out.printf("Price updated for '%s': Rs.%.2f%n", item.getName(), newPrice);
    }

    public void toggleAvailability(String itemId) {
        MenuItem item = findById(itemId);
        item.setAvailable(!item.isAvailable());
        System.out.println(item.getName() + " is now " + (item.isAvailable() ? "AVAILABLE" : "UNAVAILABLE"));
    }
}

class RestaurantManager {
    private String      restaurantName;
    private Menu        menu;
    private List<Order> orders;
    private double      taxRate;

    public RestaurantManager(String restaurantName, double taxRate) {
        if (restaurantName == null || restaurantName.isEmpty())
            throw new IllegalArgumentException("Restaurant name cannot be empty.");
        if (taxRate < 0) throw new IllegalArgumentException("Tax rate cannot be negative.");
        this.restaurantName = restaurantName;
        this.taxRate        = taxRate;
        this.menu           = new Menu(restaurantName);
        this.orders         = new ArrayList<>();
    }

    public Menu getMenu() { return menu; }

    public Order placeOrder(String customerName, String tableNumber) {
        Order order = new Order(customerName, tableNumber, taxRate);
        orders.add(order);
        System.out.println("Order placed: " + order.getOrderId() + " for " + customerName);
        return order;
    }

    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        Order order = findOrder(orderId);
        order.setStatus(newStatus);
        System.out.println("Order " + orderId + " status updated to: " + newStatus);
    }

    public Order findOrder(String orderId) {
        return orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }

    public void printAllOrders() {
        if (orders.isEmpty()) { System.out.println("No orders yet."); return; }
        System.out.println("===== All Orders =====");
        orders.forEach(System.out::println);
    }

    public double getTotalRevenue() {
        return orders.stream()
                .filter(o -> o.getStatus() != OrderStatus.CANCELLED)
                .mapToDouble(Order::getTotal)
                .sum();
    }

    public void printRevenueReport() {
        long served    = orders.stream().filter(o -> o.getStatus() == OrderStatus.SERVED).count();
        long cancelled = orders.stream().filter(o -> o.getStatus() == OrderStatus.CANCELLED).count();
        System.out.println("===== Revenue Report - " + restaurantName + " =====");
        System.out.println("Total Orders  : " + orders.size());
        System.out.println("Served        : " + served);
        System.out.println("Cancelled     : " + cancelled);
        System.out.printf ("Total Revenue : Rs.%.2f%n", getTotalRevenue());
    }
}

public class DynamicMenuSystem {
    public static void main(String[] args) {

        RestaurantManager restaurant = new RestaurantManager("Spice Garden", 5.0);
        Menu menu = restaurant.getMenu();

        System.out.println("=== Setting Up Menu ===");
        FoodItem samosa       = new FoodItem("F001", "Veg Samosa",           30.0,  Category.STARTER,     "Crispy fried pastry",         true);
        FoodItem chickenTikka = new FoodItem("F002", "Chicken Tikka",        180.0, Category.STARTER,     "Grilled chicken with spices",  false);
        FoodItem paneerButter = new FoodItem("F003", "Paneer Butter Masala", 220.0, Category.MAIN_COURSE, "Rich creamy paneer curry",     true);
        FoodItem biryani      = new FoodItem("F004", "Chicken Biryani",      280.0, Category.MAIN_COURSE, "Spiced rice with chicken",     false);
        FoodItem gulab        = new FoodItem("F005", "Gulab Jamun",          60.0,  Category.DESSERT,     "Soft sweet dumplings",         true);
        FoodItem lassi        = new FoodItem("F006", "Sweet Lassi",          80.0,  Category.BEVERAGE,    "Chilled yoghurt drink",        true);

        SpecialItem chefSpecial = new SpecialItem("S001", "Chef's Special Thali", 350.0,
                "Full meal with 5 items", "31-12-2025", 10);

        ComboItem combo = new ComboItem("C001", "Starter Combo", Category.STARTER,
                "Samosa + Lassi at a discount", 10.0);
        combo.addToCombo(samosa);
        combo.addToCombo(lassi);

        menu.addItem(samosa);
        menu.addItem(chickenTikka);
        menu.addItem(paneerButter);
        menu.addItem(biryani);
        menu.addItem(gulab);
        menu.addItem(lassi);
        menu.addItem(chefSpecial);
        menu.addItem(combo);

        System.out.println();

        System.out.println("=== Test 1: Display Full Menu (No Discount) ===");
        menu.displayMenu(new NoDiscount());

        System.out.println("=== Test 2: Display Menu (Happy Hour - 50% Off) ===");
        menu.displayMenu(new HappyHourDiscount());

        System.out.println("=== Test 3: Alice orders with 10% discount ===");
        DiscountStrategy aliceDiscount = new PercentageDiscount(10);
        Order order1 = restaurant.placeOrder("Alice", "T1");
        order1.addItem(new OrderItem(paneerButter, 2, aliceDiscount));
        order1.addItem(new OrderItem(lassi,        2, aliceDiscount));
        order1.addItem(new OrderItem(gulab,        1, new NoDiscount()));
        restaurant.updateOrderStatus(order1.getOrderId(), OrderStatus.PREPARING);
        restaurant.updateOrderStatus(order1.getOrderId(), OrderStatus.READY);
        restaurant.updateOrderStatus(order1.getOrderId(), OrderStatus.SERVED);
        System.out.println();
        order1.printBill();

        System.out.println();

        System.out.println("=== Test 4: Bob orders Chef's Special Thali ===");
        Order order2 = restaurant.placeOrder("Bob", "T2");
        order2.addItem(new OrderItem(chefSpecial, 1, new FlatDiscount(50)));
        chefSpecial.sellOne();
        System.out.println("Remaining Chef's Special: " + chefSpecial.getRemainingQuantity());
        restaurant.updateOrderStatus(order2.getOrderId(), OrderStatus.SERVED);
        order2.printBill();

        System.out.println();

        System.out.println("=== Test 5: Charlie orders the Starter Combo ===");
        Order order3 = restaurant.placeOrder("Charlie", "T3");
        order3.addItem(new OrderItem(combo,   2, new NoDiscount()));
        order3.addItem(new OrderItem(biryani, 1, new PercentageDiscount(5)));
        restaurant.updateOrderStatus(order3.getOrderId(), OrderStatus.SERVED);
        order3.printBill();

        System.out.println();

        System.out.println("=== Test 6: Dave places and cancels an order ===");
        Order order4 = restaurant.placeOrder("Dave", "T4");
        order4.addItem(new OrderItem(samosa, 3, new NoDiscount()));
        order4.cancelOrder();

        System.out.println();

        System.out.println("=== Test 7: Mark Chicken Tikka as unavailable ===");
        menu.toggleAvailability("F002");

        System.out.println();

        System.out.println("=== Test 8: Update Biryani price to Rs.320 ===");
        menu.updateItemPrice("F004", 320.0);

        System.out.println();

        System.out.println("=== Test 9: Search for 'lassi' ===");
        List<MenuItem> searchResult = menu.searchByName("lassi");
        searchResult.forEach(System.out::println);

        System.out.println();

        System.out.println("=== Test 10: Try ordering unavailable Chicken Tikka ===");
        try {
            Order order5 = restaurant.placeOrder("Eve", "T5");
            order5.addItem(new OrderItem(chickenTikka, 1, new NoDiscount()));
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        System.out.println();

        System.out.println("=== Test 11: Invalid discount (150%) ===");
        try {
            new PercentageDiscount(150);
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        System.out.println();

        System.out.println("=== Test 12: Add duplicate item ID ===");
        try {
            menu.addItem(new FoodItem("F001", "Duplicate Samosa", 30.0, Category.STARTER, "Dup item", true));
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        System.out.println();

        restaurant.printRevenueReport();

        System.out.println();

        restaurant.printAllOrders();
    }
}