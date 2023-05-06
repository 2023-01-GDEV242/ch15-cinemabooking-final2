import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;

/**
 * This is the system of the Cinema Booking final project.
 * It will use classes named Showing, Theater, Customer, Film, Booking, Row, & Seat
 * to manage a cinema's bookings.
 *
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/5/2023
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
    private HashSet<Film> currentFilms;
    private int showingID;
        
    /**
     * Creates the CinemaBookingSystem object.
     */
    public void CinemaBookingSystem() {
        theaters = new ArrayList<Theater>();
        showings = new HashSet<Showing>();
        customers = new HashSet<Customer>();
        currentFilms = new HashSet<Film>();
        showingID = 0;
        
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
     * Adds a new film to the list of currently showing films.
     */
    public void addFilm() {
        // Scanner input = new Scanner(System.in);
        // System.out.print("Enter film name: ");
        // String name = input.nextLine();
        // System.out.print("Enter film length, in minutes: ");
        // int length = input.nextInt();
        // input.nextLine();
        // Film film = new Film(name, length);
        // currentFilms.add(film);
        
        Film film1 = new Film("The Shawshank Redemption", 142);
        Film film2 = new Film("The Dark Knight", 152);
        Film film3 = new Film("The Godfather", 175);
        
        currentFilms.add(film1);
        currentFilms.add(film2);
        currentFilms.add(film3);
    }
    
    /**
     * Removes a film from the list of currently playing films.
     */
    public void removeFilm() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter film name: ");
        String name = input.nextLine();
        Iterator<Film> it = currentFilms.iterator();
        
        while (it.hasNext()) {
            Film film = it.next();
            if (film.toString().equals(name)) {
                it.remove();
                System.out.println(name + " was removed.");
            }
        }
    }
    
    /**
     * Creates a showing and adds it to the list of showings.
     */
    public void addShowing() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter film name: ");
        String name = input.nextLine();
        Iterator<Film> it = currentFilms.iterator();
        Film film = null;
        
        while (it.hasNext()) {
            Film filmCheck = it.next();
            if (film.toString().equals(name)) {
                film = filmCheck;
            }
        }
        
        if (film == null) {
            System.out.println("Film not in list.");
        }
        else {
            // test values
            // System.out.print("Enter date: ");
            // System.out.print("Enter time: ");
            int year = 2023;
            int month = 10;
            int day = 27;
            int hour = 14;
            int minute = 30;
            
            System.out.print("Enter theater number: ");
            int num = input.nextInt();
            input.nextLine();
            Iterator<Theater> iter = theaters.iterator();
            Theater theater = null;
            
            while (iter.hasNext()) {
                Theater theaterCheck = iter.next();
                if (theaterCheck.getNum() == num) {
                    theater = theaterCheck;
                }
            }
            
            if (theater == null) {
                System.out.println("Theater number not recognized.");
            }
            else{
                showingID++;
                Showing showing =
                    new Showing(showingID, film, year, month, day, hour, minute, theater);
                showings.add(showing);
            }
        }
    }
    
    /**
     * Prints the information of all current showings.
     */
    public void listShowings() {
        Iterator<Showing> it = showings.iterator();
        while (it.hasNext()) {
            Showing showing = it.next();
            System.out.println(showing.getID() + ": \"" + showing.filmName() +
                "\"\n    Theater no. " + showing.getTheater().getNum() + " at" +
                showing.getDateTime() + "\n    Open seats: " + showing.getOpenSeatNum());
        }
    }
    
    /**
     * Adds a customer to the customer registry.
     */
    public void addCustomer() {
        String name1 = "Bob";
        String name2 = "Mary";
        String name3 = "Pikachu";
        String phone1 = "999 555 1234";
        String phone2 = "908 123 4567";
        String phone3 = "917 000 5555";
        
        Customer cust1 = new Customer(name1, phone1);
        Customer cust2 = new Customer(name2, phone2);
        Customer cust3 = new Customer(name3, phone3);
        
        customers.add(cust1);
        customers.add(cust2);
        customers.add(cust3);
    }
    
    /**
     * Prints the information of a customer.
     */
    public void getCustomerInfo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = input.nextLine();
        Iterator<Customer> it = customers.iterator();
        
        while (it.hasNext()) {
            Customer customer = it.next();
            if (customer.getName().equals(name)) {
                System.out.println("Information for " + name + ":");
                System.out.println("    Phone numner: " + customer.getPhone());
                System.out.println("    Bookings:");
                ArrayList<Booking> bookings = customer.getBookings();
                for (int index = 0; index < bookings.size(); index++) {
                    System.out.println(bookings.get(index).getInfo() + "\n");
                }
            }
        }
    }
    
    /**
     * Create a booking using text input.
     */
    public void createOneBookingTextInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter showing ID: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.print("Enter seat number: ");
        String seatNo = input.nextLine();
        System.out.print("Enter customer name: ");
        String name = input.nextLine();
        Iterator<Showing> showIt = showings.iterator();
        Iterator<Customer> custIt = customers.iterator();
        Showing showing = null;
        Customer customer = null;
        
        while (showIt.hasNext()) {
            Showing showCheck = showIt.next();
            if (showCheck.getID() == id) {
                showing = showCheck;
            }
        }
        
        while (custIt.hasNext()) {
            Customer custCheck = custIt.next();
            if (custCheck.getName().equals(name)) {
                customer = custCheck;
            }
        }
        
        if ((showing != null) && (customer != null)) {
            Seat seat = showing.getSeat(seatNo);
            if (seat.isFree()) {
                seat.changeAvailability();
                Booking booking = new Booking(showing, seat);
                customer.addBooking(booking);
                System.out.println("Booking created!");
            }
            else {
                System.out.println("Seat is already booked.");
            }
        }
        else {
            System.out.println("Error in showing ID or customer name.");
        }
    }
    
    /**
     * Create a booking via parameters.
     * @param showing The booking's showing.
     * @param seatNum The booking's seat number.
     * @param customer The customer associated with the booking.
     */
    public void createBooking(Showing showing, String seatNum, Customer customer) {
        Seat seat = showing.getSeat(seatNum);
        seat.changeAvailability();
        Booking booking = new Booking(showing, seat);
        customer.addBooking(booking);
    }
    
    /**
     * Create multiple bookings for a given showing.
     * @param showing The bookings' showing.
     * @param seatNumList The bookings' seat numbers.
     * @param customer The customer associated with the booking.
     */
    public void multiBooking(Showing showing, ArrayList<String> seatNumList,
                            Customer customer) {
        for (int index = 0; index < seatNumList.size(); index++) {
            String seat = seatNumList.get(index);
            if (showing.getSeat(seat).isFree()) {
                createBooking(showing, seat, customer);
            }
            else if (!showing.getSeat(seat).isFree()) {
                System.out.println("Seat number " + seat + " is unavailable.");
            }
            else {
                System.out.println("Seat number " + seat + " is invalid.");
            }
        }
    }
    
    /**
     * Create bookings of adjacent seats.
     * @param showing The bookings' showing.
     * @param customer The customer associated with the booking.
     */
    public void bookAdjacent(Showing showing, Customer customer) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter starting seat number: ");
        String start = input.nextLine();
        System.out.print("Enter ending seat number: ");
        String end = input.nextLine();
        ArrayList<String> seats = showing.getAdjacent(start, end);
        multiBooking(showing, seats, customer);
    }
    
    /**
     * Cancels a single booking.
     * @param customer The customer associated with the booking.
     * @param showing The showing associated with the booking.
     */
    public void cancelOneBooking(Customer customer, Booking booking) {
        booking.getSeat().changeAvailability();
        customer.removeBooking(booking);
    }
    
    /**
     * Cancels all of the bookings a customer has for a given showing.
     * @param customer The customer associated with the bookings.
     * @param showing The showing associated with the bookings.
     */
    public void cancelAllBookings(Customer customer, Showing showing) {
        Iterator<Booking> bookings = customer.getBookings().iterator();
        while (bookings.hasNext()) {
            Booking booking = bookings.next();
            if (booking.getShowing() == showing) {
                cancelOneBooking(customer, booking);
            }
        }
    }
    
    /**
     * Prints the details of a given showing.
     * @param showing The showing in question.
     */
    public void printShowDetail(Showing showing) {
        String info = showing.showDetail();
        System.out.println(info);
    }
    
    /**
     * Finds the adjacent seats for a given showing and prints them.
     * @param showing The showing being examined.
     */
    public void findAdjSeat(Showing showing) {
        ArrayList<String> adjSeats = showing.findAdjacent();
        if (adjSeats.size() > 0) {
            System.out.println("Adjacent seats:");
            for (int index = 0; index < adjSeats.size(); index++) {
                System.out.println(adjSeats.get(index));
            }
        }
        else {
            System.out.println("No adjacent seats for this showing.");
        }
    }
    
    /**
     * 
     */
}

















