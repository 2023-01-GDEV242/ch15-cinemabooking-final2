import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Customer class
 * @author Final2Group
 */
public class Customer {

    private String name;
    private List<Booking> bookings;

    /**
     * Constructor for objects of class Customer.
     * @param name The name of the customer.
     */
    public Customer(String name) {
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    /**
     * Displays a list of available showings.
     * @param showings The list of available showings to display.
     */
    public void viewShowings(List<Showing> showings) {
        System.out.println("Available showings:");
        for (Showing showing : showings) {
            System.out.println(showing);
        }
    }

    /**
     * Reserves a booking for the customer.
     * @param showing The showing to reserve a booking for.
     * @param cinema The cinema where the booking is being made.
     */
    public void reserveBooking(Showing showing, Theater cinema) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter row number:");
        int row = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        Seat seat;

        System.out.println("Enter seat number:");
        int seatNumber = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        seat = new Seat(String.valueOf(row), seatNumber);

        Booking booking = new Booking(showing, seat);
        cinema.addBooking(booking);
        bookings.add(booking);

        System.out.println("Booking created: " + booking);
    }

    /**
     * Returns a list of all bookings made by the customer.
     * @return The list of all bookings made by the customer.
     */
    public List<Booking> getBookings() {
        return bookings;
    }
}

