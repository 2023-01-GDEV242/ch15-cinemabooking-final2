import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This is the overall system of the Cinema Booking fianal project. It will hold methods & classes named:
 * Showing Collections, Showing, Theater, Customer, & Showing/theater map
 *
 * Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * 5/1/2023
 */
public class CinemaBookingSystem {
    // instance variables for the system
    // private ShowingCollection amount;
    // private Showing showing; // showing is place holder for now
    // private Theater status; // used status here because I thought Cinema would check if theater can show another movie or not
    // private Customer customer;
    
    private ArrayList<Theater> theaters;
    private HashSet<Showing> showings;
    private HashSet<Customer> customers;
        
    /**
     * Constructor for objects of class CinemaBookingSystem
     */
    public void CinemaBookingSystem() {
        theaters = new ArrayList<Theater>();
        showings = new HashSet<Showing>();
        customers = new HashSet<Customer>();
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter number of theaters: ");
        int numOfTheaters = input.nextInt();
        input.nextLine();
        
        for (int index = 0; index < numOfTheaters; index++) {
            System.out.printf("For theater number %d," +
                "enter its number of rows: ", (index+1));
            int rows = input.nextInt();
            System.out.printf("For theater number %d," +
                "enter its number of seats per row: ", (index+1));
            int seats = input.nextInt();
            Theater newTheater = new Theater((index+1), rows, seats);
            theaters.add(newTheater);
        }
    }
    
    /**
     * This places movies in a collection and assign it to a theater
     */
    public void addShowing() {}
    
    public void addCustomer() {}
    
    public void getCustomerInfo() {}
    
    public void addBooking(Booking booking) {}
}