class Ticket {
    private String passengerName;
    private int availableSeats = 5; 
    private int bookedSeats;
    public int getAvailableSeats() {
        return availableSeats;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String name) {
        this.passengerName = name;
    }
    public void bookTicket(int seats) {
        if (seats <= availableSeats) {
            bookedSeats = seats;
            availableSeats -= seats;
            System.out.println("Ticket booked successfully for " + passengerName);
        } else {
            System.out.println("Not enough seats available!");
        }
    }
    void display() {
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Booked Seats: " + bookedSeats);
        System.out.println("Remaining Seats: " + availableSeats);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ticket t = new Ticket();

        try {
            System.out.println("Enter Passenger Name:");
            String name = sc.nextLine();
            t.setPassengerName(name);

            System.out.println("Enter number of seats to book:");
            int seats = sc.nextInt();

            t.bookTicket(seats);

            t.display();
        } 
        catch (Exception e) {
            System.out.println("Invalid input! Please enter correct data.");
        } 
        finally {
            System.out.println("Thank you for using Ticket Booking System.");
        }
        sc.close();
    }
}