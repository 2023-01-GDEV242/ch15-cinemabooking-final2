import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.time.LocalDateTime;

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
    private String email;
    
    /**
     * Creates the CinemaBookingSystem object, with text input for certain parameters.
     */
    public CinemaBookingSystem() {
        theaters = new ArrayList<Theater>();
        showings = new HashSet<Showing>();
        customers = new HashSet<Customer>();
        currentFilms = new HashSet<Film>();
        showingID = 1;
        
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter number of theaters: ");
        int numOfTheaters = input.nextInt();
        input.nextLine();
        
        for (int index = 0; index < numOfTheaters; index++) {
            System.out.printf("For theater number %d, " +
                "enter its number of rows: ", (index+1));
            int rows = input.nextInt();
            System.out.printf("For theater number %d, " +
                "enter its number of seats per row: ", (index+1));
            int seats = input.nextInt();
            Theater newTheater = new Theater((index+1), rows, seats);
            theaters.add(newTheater);
        }
    }
    
    /**
     * Creates the CinemaBookingSystem object, with parameters, assuming identical theaters.
     */
    public CinemaBookingSystem(int numOfTheaters, int rows, int seats) {
        theaters = new ArrayList<Theater>();
        showings = new HashSet<Showing>();
        customers = new HashSet<Customer>();
        currentFilms = new HashSet<Film>();
        showingID = 1;
        
        for (int index = 0; index < numOfTheaters; index++) {
            Theater newTheater = new Theater((index+1), rows, seats);
            theaters.add(newTheater);
        }
    }
    
    /**
     * Creates data for the system's parameters for ease of testing.
     */
    public void populateTestData() {
        Film film1 = new Film("The Shawshank Redemption", 142);
        Film film2 = new Film("The Dark Knight", 152);
        Film film3 = new Film("The Godfather", 175);
        currentFilms.add(film1);
        currentFilms.add(film2);
        currentFilms.add(film3);
        
        Customer cust1 = new Customer("Bob", "999 555 1234");
        Customer cust2 = new Customer("Mary", "908 123 4567");
        Customer cust3 = new Customer("Pikachu", "917 000 5555");
        addCustomer(cust1);
        addCustomer(cust2);
        addCustomer(cust3);
        
        if (theaters.size() >= 1) {
            Showing show1 = new Showing(showingID, film1,
                                        2023, 5, 10, 12, 30, theaters.get(0));
            showingID++;
            showings.add(show1);
            if (theaters.size() >= 2) {
                Showing show2 = new Showing(showingID, film2,
                                        2023, 5, 11, 14, 30, theaters.get(1));
                showingID++;
                showings.add(show2);
                if (theaters.size() >= 3) {
                    Showing show3 = new Showing(showingID, film3,
                                        2023, 5, 12, 16, 30, theaters.get(2));
                    showingID++;
                    showings.add(show3);
                }
            }
        }
    }
    
    /**
     * Adds a new film to the list of currently showing films.
     */
    public void addFilm() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter film name: ");
        String name = input.nextLine();
        System.out.print("Enter film length, in minutes: ");
        int length = input.nextInt();
        input.nextLine();
        Film film = new Film(name, length);
        currentFilms.add(film);
    }
    
    /**
     * Removes a film from the list of currently playing films.
     */
    public void removeFilm() {
        Film film = getFilm();
        if (film != null) {
            currentFilms.remove(film);
            System.out.println(film.toString() + " was removed.");
        }
        else {
            System.out.println("Film not on list of current films.");
        }
    }
    
    /**
     * Creates a showing and adds it to the list of showings.
     */
    public void addShowing() {
        Film film = getFilm();
        
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
            
            Theater theater = getTheater();
            
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
            System.out.println(showing.lessDetail());
        }
    }
    
    /**
     * Adds a customer to the customer registry (via text input).
     */
    public void addCustomerText() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = input.nextLine();
        System.out.print("Enter customer phone number: ");
        String number = input.nextLine();
        addCustomer(new Customer(name, number));
    }
    
    /**
     * Adds a customer to the customer registry (via parameter).
     * @param customer The customer being registered.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    /**
     * Prints the information of a customer.
     */
    public void getCustomerInfo() {
        Customer customer = getCustomer();
        if (customer != null) {
                System.out.println("Information for " + customer.getName() + ":");
                System.out.println("    Phone numner: " + customer.getPhone());
                System.out.println("    Bookings:");
                ArrayList<Booking> custBookings = customer.getBookings();
                for (Showing showing : showings) {
                    boolean hasBookings = false;
                    String seatList = "";
                    Set<Booking> showBookings = showing.getBookings();
                    for (Booking booking : custBookings) {
                        if (showBookings.contains(booking)) {
                            ArrayList<Seat> bookSeats = booking.getSeats();
                            for (Seat seat : bookSeats) {
                                seatList += seat.getNum() + " ";
                            }
                            hasBookings = true;
                        }
                    }
                    if (hasBookings) {
                        System.out.println(showing.minDetail());
                        System.out.println("Seats: " + seatList);
                    }
                }
        }
        else {
            System.out.println("Customer not found.");
        }
    }
    
    /**
     * Returns a theater according to number, given via text input.
     * @return A theater according to number, given via text input.
     */
    public Theater getTheater() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter theater number: ");
        int num = input.nextInt() - 1;
        input.nextLine();
        Theater theater = null;
        if ((num >= 0) && (num < theaters.size())) {
            theater = theaters.get(num);
        }
        return theater;
    }
    
    /**
     * Returns a showing according to ID, given via text input.
     * @return A showing according to ID, given via text input.
     */
    public Showing getShowing() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter showing ID: ");
        int id = input.nextInt();
        input.nextLine();
        Showing showing = null;
        Iterator<Showing> showIt = showings.iterator();
        while (showIt.hasNext()) {
            Showing showCheck = showIt.next();
            if (showCheck.getID() == id) {
                showing = showCheck;
            }
        }
        return showing;
    }
    
    /**
     * Returns a customer according to name, given via text input.
     * @return A customer according to name, given via text input.
     */
    public Customer getCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = input.nextLine();
        Iterator<Customer> custIt = customers.iterator();
        Customer customer = null;
        
        while (custIt.hasNext()) {
            Customer custCheck = custIt.next();
            if (custCheck.getName().equals(name)) {
                customer = custCheck;
            }
        }
        
        return customer;
    }
    
    /**
     * Acquires input and describing a seat number and returns it as a string.
     * @return The seat number.
     */
    public String getSeatInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter seat number: ");
        String seatNo = input.nextLine();
        return seatNo;
    }
    
    /**
     * Returns a film according to name, given via text input.
     * @return A film according to name, given via text input.
     */
    public Film getFilm() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter film name: ");
        String name = input.nextLine();
        Iterator<Film> it = currentFilms.iterator();
        Film film = null;
        
        while (it.hasNext()) {
            Film check = it.next();
            if (check.toString().equals(name)) {
                film = check;
            }
        }
        
        return film;
    }
    
    /**
     * Create a booking for one seat using text input.
     */
    public void createOneBookingTextInput() {
        Showing showing = getShowing();
        Customer customer = getCustomer();
        String seatNo = getSeatInput();
        
        if ((showing != null) && (customer != null)) {
            Seat seat = showing.getSeat(seatNo);
            if (seat != null) {
                if (seat.isFree()) {
                    ArrayList<Seat> seats = new ArrayList<Seat>();
                    seats.add(seat);
                    Booking booking = new Booking(seats);
                    customer.addBooking(booking);
                    System.out.println("Booking created!");
                }
                else {
                    System.out.println("Seat is already booked.");
                }
            }
            else {
                System.out.println("Seat does not exist.");
            }
        }
        else {
            System.out.println("Error in showing ID or customer name.");
        }
    }
    
    /**
     * Create a booking via parameters for one seat.
     * @param showing The booking's showing.
     * @param seatNum The booking's seat number.
     * @param customer The customer associated with the booking.
     */
    public void createBooking(Showing showing, String seatNum, Customer customer) {
        Seat seat = showing.getSeat(seatNum);
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        seatList.add(seat);
        showing.addBooking(seatList, customer);
    }
    
    /**
     * Create multiple bookings for a given showing.
     * @param showing The bookings' showing.
     * @param seatNumList The bookings' seat numbers.
     * @param customer The customer associated with the booking.
     */
    public void multiBooking(Showing showing, ArrayList<String> seatNumList,
                            Customer customer) {
        ArrayList<Seat> bookingSeats = new ArrayList<Seat>();
        for (int index = 0; index < seatNumList.size(); index++) {
            String seatNum = seatNumList.get(index);
            Seat seat = showing.getSeat(seatNum);
            if (seat.isFree()) {
                bookingSeats.add(seat);
            }
            else if (!seat.isFree()) {
                System.out.println("Seat number " + seat + " is unavailable.");
            }
            else {
                System.out.println("Seat number " + seat + " is invalid.");
            }
        }
        showing.addBooking(bookingSeats, customer);
    }
    
    /**
     * Create booking of adjacent seats;
     * showing and customer are entered as parameters.
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
     * Create booking of adjacent seats;
     * showing and customer are entered by text.
     */
    public void bookAdjText() {
        Customer customer = getCustomer();
        Showing showing = getShowing();
        bookAdjacent(showing, customer);
    }
    
    /**
     * Cancels a set of seats from their bookings.
     * @param cancelledSeats The seats to be cancelled.
     * @param showing The showing associated with the booking.
     */
    public void cancelSeats(Showing showing, ArrayList<Seat> cancelledSeats) {
        showing.cancelBooking(cancelledSeats);
    }
    
    /**
     * Cancels all of the bookings a customer has for a given showing.
     * @param customer The customer associated with the bookings.
     * @param showing The showing associated with the bookings.
     */
    public void cancelBookingsForCustAndShow(Customer customer, Showing showing) {
        Set<Booking> showBookings = showing.getBookings();
        ArrayList<Booking> custBookings = customer.getBookings();
        for (Booking booking : custBookings) {
            if (showBookings.contains(booking)) {
                customer.removeBooking(booking);
            }
        }
    }
    
    /**
     * Prints the details of a given showing.
     * @param showing The showing in question.
     */
    public void printShowDetail(Showing showing) {
        System.out.println(showing.showDetail());
    }
    
    /**
     * Prints the details of a showing selected through text input.
     */
    public void printShowDetailText() {
        Showing showing = getShowing();
        printShowDetail(showing);
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
     * Finds the adjacent seats for a showing via text input.
     */
    public void findAdjSeatText() {
        Showing showing = getShowing();
        findAdjSeat(showing);
    }
    
    /**
     * Prints the showings for a given film, given via text input.
     */
    public void listShowingsByFilm() {
        Film film = getFilm();
        
        if (film != null) {
            listShowingsForFilm(film);
        }
        else {
            System.out.println("Film not currently playing.");
        }
    }
    
    /**
     * Prints the showings for a given film, given via parameter.
     * @param film The film used to filter results.
     */
    public void listShowingsForFilm(Film film) {
        Iterator<Showing> it = showings.iterator();
        System.out.println("Showings for \"" + film.toString() + "\":");
        while (it.hasNext()) {
            Showing showing = it.next();
            if (showing.getFilm() == film) {
                System.out.println(showing.lessDetail() + "\n");
            }
        }
    }
        
    /**
     * Prints the showings for a given date and time, given via text input.
     */
    public void printShowingsByTime() {
        Scanner input = new Scanner(System.in);
        int year = 2023;
        System.out.print("Enter month: ");
        int month = input.nextInt();
        input.nextLine();
        System.out.print("Enter day: ");
        int day = input.nextInt();
        input.nextLine();
        System.out.print("Enter hour: ");
        int hour = input.nextInt();
        input.nextLine();
        System.out.print("Enter minute: ");
        int minute = input.nextInt();
        input.nextLine();
        
        LocalDate date = LocalDate.of(year, month, day);
        LocalTime time = LocalTime.of(hour, minute);
        
        ArrayList<Showing> list = listShowingsForTime(date, time);
        
        System.out.println("Showings for " + date + ", " + time + " and one hour after:");
        for (Showing showing : list) {
            System.out.println(showing.lessDetail() + "\n");
        }
    }
    
    /**
     * Prints the showings for a given date and time, given via text input.
     * @param date The showing date.
     * @param time The showing time, or up to one hour earlier.
     */
    public ArrayList<Showing> listShowingsForTime(LocalDate date, LocalTime time) {
        ArrayList<Showing> showList = new ArrayList<Showing>();
        LocalTime start = time.minusMinutes(15);
        LocalTime end = time.plusHours(1);
        Iterator<Showing> it = showings.iterator();
        while (it.hasNext()) {
            Showing showing = it.next();
            if (showing.getDate().equals(date)) {
                LocalTime showtime = showing.getTime();
                if ((showtime.isAfter(start)) && (showtime.isBefore(end))){
                    showList.add(showing);
                }
            }
        }
        return showList;
    }
    
    /**
     * Gets a list of all occupied theaters at the cinema for a given time.
     * @param time the time to search for
     * @return a list of all occupied theaters at the specified time
     */
    public List<Theater> getOccupiedTheaters(LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();
        List<Showing> showingsForTime = listShowingsForTime(date, time);
        List<Theater> occupiedTheaters = new ArrayList<>();
        for (Showing showing : showingsForTime) {
            Theater theater = showing.getTheater();
            if (!occupiedTheaters.contains(theater)) {
                occupiedTheaters.add(theater);
            }
        }
        return occupiedTheaters;
    }

    /**
     * Gets a list of all empty theaters at the cinema for a given time.
     * @param time the time to search for
     * @return a list of all empty theaters at the specified time
     */
    public List<Theater> getEmptyTheaters(LocalDateTime time) {
        List<Theater> occupiedTheaters = getOccupiedTheaters(time);
        List<Theater> emptyTheaters = new ArrayList<>(theaters);
        emptyTheaters.removeAll(occupiedTheaters);
        return emptyTheaters;
    }
    
    /**
     * Removes a showing from the cinema booking system and contacts affected customers.
     * @param showing The showing to be canceled.
     */
     public void cancelShowing(Showing showing) {
        Collection<Customer> affectedCust = showing.getAllCust();
        System.out.println("Affected customers:");
        for (Customer customer : affectedCust) {
            System.out.println(customer.getName() + ", " + customer.getPhone());
        }
        showing.cancelAll();
        showings.remove(showing);
    }
    
    // /**
     // * Retrieves all bookings made by customers in the cinema booking system.
     // * 
     // * @return a list of all bookings made by customers
     // */
    // public List<Booking> getBookings() {
        // List<Booking> bookings = new ArrayList<>();
        // for (Customer customer : customers) {
            // bookings.addAll(customer.getBookings());
        // }
        // return bookings;
    // }
    
    // /**
     // * Retrieves a customer object from the cinema booking system based on their email address.
     // * 
     // * @param email the email address of the customer to retrieve
     // * @return the customer object associated with the specified email address, or null if no such customer is found
     // */
    // public Customer getCustomer(String email) {
        // for (Customer customer : customers) {
            // if (customer.getEmail().equals(email)) {
                    // return customer;
            // }
        // }
        // return null; // customer not found
    // }
    
    // /**
     // * Retrieves information about a customer in the cinema booking system based on their email address.
     // * 
     // * @param email the email address of the customer to retrieve information for
     // * @return a string containing the name and email address of the customer, or "Customer not found" if no such customer is found
     // */
     // public String getCustomerInfo(String email) {
        // Customer customer = getCustomer(email);
        // if (customer == null) {
            // return "Customer not found";
        // } else {
            // return customer.getName() + " (" + customer.getEmail() + ")";
        // }
    // }
    
    /**
     * Retrieves the email address associated with a customer object.
     * 
     * @return the email address associated with this customer
     */
    public String getEmail() {
        return email;
    }

}







