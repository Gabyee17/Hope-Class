import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ─────────────────────────────────────────────
//  ENUMS
// ─────────────────────────────────────────────

enum VehicleType {
    BIKE, AUTO, MINI, SEDAN, SUV
}

enum RideStatus {
    REQUESTED, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED
}

// ─────────────────────────────────────────────
//  FARE CALCULATOR INTERFACE (Abstraction)
// ─────────────────────────────────────────────

interface FareCalculator {
    double calculateFare(double distanceKm);
}

// Polymorphism: each vehicle type has its own fare logic
class BikeFare implements FareCalculator {
    public double calculateFare(double distanceKm) { return 10 + (distanceKm * 5); }
}

class AutoFare implements FareCalculator {
    public double calculateFare(double distanceKm) { return 15 + (distanceKm * 8); }
}

class MiniFare implements FareCalculator {
    public double calculateFare(double distanceKm) { return 20 + (distanceKm * 10); }
}

class SedanFare implements FareCalculator {
    public double calculateFare(double distanceKm) { return 30 + (distanceKm * 14); }
}

class SuvFare implements FareCalculator {
    public double calculateFare(double distanceKm) { return 50 + (distanceKm * 18); }
}

// ─────────────────────────────────────────────
//  VEHICLE CLASS (Encapsulation)
// ─────────────────────────────────────────────

class Vehicle {
    private String vehicleId;
    private String model;
    private String licensePlate;
    private VehicleType type;

    public Vehicle(String vehicleId, String model, String licensePlate, VehicleType type) {
        if (model == null || model.isEmpty())        throw new IllegalArgumentException("Model cannot be empty.");
        if (licensePlate == null || licensePlate.isEmpty()) throw new IllegalArgumentException("License plate cannot be empty.");
        this.vehicleId    = vehicleId;
        this.model        = model;
        this.licensePlate = licensePlate;
        this.type         = type;
    }

    public String      getVehicleId()    { return vehicleId; }
    public String      getModel()        { return model; }
    public String      getLicensePlate() { return licensePlate; }
    public VehicleType getType()         { return type; }

    public String toString() {
        return String.format("Vehicle[%s | %s | %s | %s]", vehicleId, model, licensePlate, type);
    }
}

// ─────────────────────────────────────────────
//  ABSTRACT PERSON CLASS (Abstraction + Encapsulation)
// ─────────────────────────────────────────────

abstract class Person {
    private String personId;
    private String name;
    private String email;
    private String phone;

    public Person(String personId, String name, String email, String phone) {
        if (name == null || name.isEmpty())   throw new IllegalArgumentException("Name cannot be empty.");
        if (!email.contains("@"))             throw new IllegalArgumentException("Invalid email: " + email);
        if (!phone.matches("\\d{10}"))        throw new IllegalArgumentException("Phone must be 10 digits.");
        this.personId = personId;
        this.name     = name;
        this.email    = email;
        this.phone    = phone;
    }

    public String getPersonId() { return personId; }
    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public String getPhone()    { return phone; }

    public void setEmail(String email) {
        if (!email.contains("@")) throw new IllegalArgumentException("Invalid email.");
        this.email = email;
    }

    public void setPhone(String phone) {
        if (!phone.matches("\\d{10}")) throw new IllegalArgumentException("Phone must be 10 digits.");
        this.phone = phone;
    }

    // Abstract method — Polymorphism
    public abstract String getRole();

    public String toString() {
        return String.format("[%s] ID: %s | Name: %s | Email: %s | Phone: %s",
                getRole(), personId, name, email, phone);
    }
}

// ─────────────────────────────────────────────
//  RIDER CLASS (Inheritance)
// ─────────────────────────────────────────────

class Rider extends Person {
    private double walletBalance;
    private List<Ride> rideHistory;

    public Rider(String personId, String name, String email, String phone, double initialBalance) {
        super(personId, name, email, phone);
        if (initialBalance < 0) throw new IllegalArgumentException("Balance cannot be negative.");
        this.walletBalance = initialBalance;
        this.rideHistory   = new ArrayList<>();
    }

    public String getRole() { return "Rider"; }

    public double getWalletBalance() { return walletBalance; }

    public void topUpWallet(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Top-up amount must be positive.");
        walletBalance += amount;
        System.out.println(getName() + " topped up ₹" + amount + ". New balance: ₹" + walletBalance);
    }

    public void deductFare(double fare) {
        if (fare > walletBalance) throw new IllegalStateException("Insufficient wallet balance.");
        walletBalance -= fare;
    }

    public void addRideToHistory(Ride ride) { rideHistory.add(ride); }

    public void printRideHistory() {
        if (rideHistory.isEmpty()) { System.out.println(getName() + " has no ride history."); return; }
        System.out.println("--- Ride History for " + getName() + " ---");
        for (Ride r : rideHistory) System.out.println(r);
    }
}

// ─────────────────────────────────────────────
//  DRIVER CLASS (Inheritance)
// ─────────────────────────────────────────────

class Driver extends Person {
    private String  licenseNumber;
    private Vehicle vehicle;
    private boolean isAvailable;
    private double  totalEarnings;
    private double  ratingSum;
    private int     totalRatings;
    private List<Ride> rideHistory;

    public Driver(String personId, String name, String email, String phone,
                  String licenseNumber, Vehicle vehicle) {
        super(personId, name, email, phone);
        if (licenseNumber == null || licenseNumber.isEmpty())
            throw new IllegalArgumentException("License number cannot be empty.");
        this.licenseNumber = licenseNumber;
        this.vehicle       = vehicle;
        this.isAvailable   = true;
        this.totalEarnings = 0;
        this.ratingSum     = 0;
        this.totalRatings  = 0;
        this.rideHistory   = new ArrayList<>();
    }

    public String  getRole()           { return "Driver"; }
    public boolean isAvailable()       { return isAvailable; }
    public void    setAvailable(boolean a) { this.isAvailable = a; }
    public Vehicle getVehicle()        { return vehicle; }
    public double  getTotalEarnings()  { return totalEarnings; }
    public String  getLicenseNumber()  { return licenseNumber; }

    public double getRating() {
        return totalRatings == 0 ? 0.0 : ratingSum / totalRatings;
    }

    public void addEarnings(double amount) { totalEarnings += amount; }

    public void receiveRating(int stars) {
        if (stars < 1 || stars > 5) throw new IllegalArgumentException("Rating must be between 1 and 5.");
        ratingSum += stars;
        totalRatings++;
    }

    public void addRideToHistory(Ride ride) { rideHistory.add(ride); }

    public void printRideHistory() {
        if (rideHistory.isEmpty()) { System.out.println(getName() + " has no ride history."); return; }
        System.out.println("--- Ride History for Driver " + getName() + " ---");
        for (Ride r : rideHistory) System.out.println(r);
    }

    public String toString() {
        return super.toString() + String.format(" | License: %s | Vehicle: %s | Rating: %.1f",
                licenseNumber, vehicle.getModel(), getRating());
    }
}

// ─────────────────────────────────────────────
//  RIDE CLASS
// ─────────────────────────────────────────────

class Ride {
    private static int counter = 1000;

    private String     rideId;
    private Rider      rider;
    private Driver     driver;
    private String     pickupLocation;
    private String     dropLocation;
    private double     distanceKm;
    private double     fare;
    private RideStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime completedAt;

    public Ride(Rider rider, String pickupLocation, String dropLocation, double distanceKm) {
        if (rider == null)                              throw new IllegalArgumentException("Rider cannot be null.");
        if (distanceKm <= 0)                            throw new IllegalArgumentException("Distance must be positive.");
        if (pickupLocation == null || pickupLocation.isEmpty()) throw new IllegalArgumentException("Pickup location required.");
        if (dropLocation == null   || dropLocation.isEmpty())   throw new IllegalArgumentException("Drop location required.");

        this.rideId         = "RIDE" + (++counter);
        this.rider          = rider;
        this.pickupLocation = pickupLocation;
        this.dropLocation   = dropLocation;
        this.distanceKm     = distanceKm;
        this.status         = RideStatus.REQUESTED;
        this.requestedAt    = LocalDateTime.now();
    }

    public String     getRideId()     { return rideId; }
    public Rider      getRider()      { return rider; }
    public Driver     getDriver()     { return driver; }
    public RideStatus getStatus()     { return status; }
    public double     getFare()       { return fare; }
    public double     getDistanceKm() { return distanceKm; }

    public void assignDriver(Driver driver) {
        if (driver == null)          throw new IllegalArgumentException("Driver cannot be null.");
        if (!driver.isAvailable())   throw new IllegalStateException("Driver is not available.");
        this.driver = driver;
        this.status = RideStatus.ACCEPTED;
        driver.setAvailable(false);
        System.out.println("Driver " + driver.getName() + " assigned to ride " + rideId);
    }

    public void startRide() {
        if (status != RideStatus.ACCEPTED)
            throw new IllegalStateException("Ride must be accepted before starting.");
        status = RideStatus.IN_PROGRESS;
        System.out.println("Ride " + rideId + " started.");
    }

    public void completeRide(FareCalculator fareCalculator) {
        if (status != RideStatus.IN_PROGRESS)
            throw new IllegalStateException("Ride must be in progress to complete.");
        this.fare = Math.round(fareCalculator.calculateFare(distanceKm) * 100.0) / 100.0;
        rider.deductFare(fare);
        driver.addEarnings(fare * 0.80);   // Driver gets 80%
        driver.setAvailable(true);
        rider.addRideToHistory(this);
        driver.addRideToHistory(this);
        status      = RideStatus.COMPLETED;
        completedAt = LocalDateTime.now();
        System.out.println("Ride " + rideId + " completed. Fare: ₹" + fare);
    }

    public void cancelRide() {
        if (status == RideStatus.COMPLETED || status == RideStatus.CANCELLED)
            throw new IllegalStateException("Cannot cancel a completed or already cancelled ride.");
        if (driver != null) driver.setAvailable(true);
        status = RideStatus.CANCELLED;
        System.out.println("Ride " + rideId + " cancelled.");
    }

    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return String.format("Ride[%s | %s -> %s | %.1f km | ₹%.2f | %s | Requested: %s]",
                rideId, pickupLocation, dropLocation, distanceKm, fare, status,
                requestedAt.format(fmt));
    }
}

// ─────────────────────────────────────────────
//  RIDE SHARING APP MANAGER
// ─────────────────────────────────────────────

class RideSharingApp {
    private List<Rider>  riders;
    private List<Driver> drivers;
    private List<Ride>   allRides;

    public RideSharingApp() {
        riders   = new ArrayList<>();
        drivers  = new ArrayList<>();
        allRides = new ArrayList<>();
    }

    public void registerRider(Rider rider) {
        riders.add(rider);
        System.out.println("Rider registered: " + rider.getName());
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
        System.out.println("Driver registered: " + driver.getName());
    }

    public Optional<Driver> findAvailableDriver(VehicleType type) {
        return drivers.stream()
                .filter(d -> d.isAvailable() && d.getVehicle().getType() == type)
                .findFirst();
    }

    public Ride bookRide(Rider rider, String pickup, String drop, double distance, VehicleType type) {
        Ride ride = new Ride(rider, pickup, drop, distance);
        allRides.add(ride);
        Driver driver = findAvailableDriver(type)
                .orElseThrow(() -> new IllegalStateException("No available " + type + " driver found."));
        ride.assignDriver(driver);
        return ride;
    }

    // Polymorphism — returns the right fare strategy based on vehicle type
    public FareCalculator getFareCalculator(VehicleType type) {
        switch (type) {
            case BIKE:  return new BikeFare();
            case AUTO:  return new AutoFare();
            case MINI:  return new MiniFare();
            case SEDAN: return new SedanFare();
            case SUV:   return new SuvFare();
            default:    throw new IllegalArgumentException("Unknown vehicle type.");
        }
    }

    public void rateDriver(Ride ride, int stars) {
        if (ride.getStatus() != RideStatus.COMPLETED)
            throw new IllegalStateException("Can only rate after ride is completed.");
        ride.getDriver().receiveRating(stars);
        System.out.printf("Driver %s rated %d/5 stars%n", ride.getDriver().getName(), stars);
    }

    public void printAllRides() {
        if (allRides.isEmpty()) { System.out.println("No rides yet."); return; }
        System.out.println("===== All Rides =====");
        allRides.forEach(System.out::println);
    }

    public void printAllDrivers() {
        System.out.println("===== Registered Drivers =====");
        drivers.forEach(System.out::println);
    }

    public void printAllRiders() {
        System.out.println("===== Registered Riders =====");
        riders.forEach(System.out::println);
    }
}

// ─────────────────────────────────────────────
//  MAIN — TEST CASES
// ─────────────────────────────────────────────

public class RideSharingApp_Main {
    public static void main(String[] args) {

        RideSharingApp app = new RideSharingApp();

        // Register Riders
        Rider alice = new Rider("R001", "Alice Kumar", "alice@gmail.com", "9876543210", 500.0);
        Rider bob   = new Rider("R002", "Bob Raj",     "bob@gmail.com",   "9123456780", 200.0);
        app.registerRider(alice);
        app.registerRider(bob);

        // Register Vehicles & Drivers
        Vehicle bike  = new Vehicle("V001", "Honda Activa", "TN01AB1234", VehicleType.BIKE);
        Vehicle auto  = new Vehicle("V002", "Bajaj Auto",   "TN02CD5678", VehicleType.AUTO);
        Vehicle sedan = new Vehicle("V003", "Swift Dzire",  "TN03EF9012", VehicleType.SEDAN);

        Driver ravi  = new Driver("D001", "Ravi Shankar", "ravi@gmail.com",  "9000011111", "LIC001", bike);
        Driver kumar = new Driver("D002", "Kumar Das",    "kumar@gmail.com", "9000022222", "LIC002", auto);
        Driver priya = new Driver("D003", "Priya Singh",  "priya@gmail.com", "9000033333", "LIC003", sedan);
        app.registerDriver(ravi);
        app.registerDriver(kumar);
        app.registerDriver(priya);

        System.out.println();

        // Test 1: Normal bike ride
        System.out.println("=== Test 1: Alice books a BIKE ride ===");
        try {
            Ride ride1 = app.bookRide(alice, "RS Puram", "Gandhipuram", 5.0, VehicleType.BIKE);
            ride1.startRide();
            ride1.completeRide(app.getFareCalculator(VehicleType.BIKE));
            app.rateDriver(ride1, 5);
            System.out.println("Alice's wallet after ride: ₹" + alice.getWalletBalance());
            System.out.println("Ravi's earnings: ₹"          + ravi.getTotalEarnings());
            System.out.println("Ravi's rating: "             + ravi.getRating());
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }

        System.out.println();

        // Test 2: Auto ride
        System.out.println("=== Test 2: Bob books an AUTO ride ===");
        try {
            Ride ride2 = app.bookRide(bob, "Peelamedu", "Airport", 8.0, VehicleType.AUTO);
            ride2.startRide();
            ride2.completeRide(app.getFareCalculator(VehicleType.AUTO));
            app.rateDriver(ride2, 4);
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }

        System.out.println();

        // Test 3: Cancel a ride
        System.out.println("=== Test 3: Alice cancels a SEDAN ride ===");
        try {
            Ride ride3 = app.bookRide(alice, "Saibaba Colony", "Town Hall", 6.0, VehicleType.SEDAN);
            ride3.cancelRide();
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }

        System.out.println();

        // Test 4: Insufficient balance
        System.out.println("=== Test 4: Bob tries SEDAN ride with low balance ===");
        try {
            Ride ride4 = app.bookRide(bob, "Tidel Park", "Railway Station", 20.0, VehicleType.SEDAN);
            ride4.startRide();
            ride4.completeRide(app.getFareCalculator(VehicleType.SEDAN));
        } catch (Exception e) { System.out.println("Caught expected error: " + e.getMessage()); }

        System.out.println();

        // Test 5: No driver available
        System.out.println("=== Test 5: Book SUV when no driver registered ===");
        try {
            app.bookRide(alice, "Singanallur", "Ukkadam", 3.0, VehicleType.SUV);
        } catch (Exception e) { System.out.println("Caught expected error: " + e.getMessage()); }

        System.out.println();

        // Test 6: Wallet top-up
        System.out.println("=== Test 6: Bob tops up wallet ===");
        bob.topUpWallet(300.0);
        System.out.println("Bob's new balance: ₹" + bob.getWalletBalance());

        System.out.println();

        // Test 7: Invalid rating
        System.out.println("=== Test 7: Invalid rating (6 stars) ===");
        try {
            ravi.receiveRating(6);
        } catch (Exception e) { System.out.println("Caught expected error: " + e.getMessage()); }

        System.out.println();

        // Ride histories
        System.out.println("=== Ride Histories ===");
        alice.printRideHistory();
        bob.printRideHistory();
        ravi.printRideHistory();

        System.out.println();
        app.printAllRides();
    }
}