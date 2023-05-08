import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Objects of this class represent a given showing at a cinema,
 * described by theater (location) and date/time.
 * Holds a collection of seats that can be marked available or unavailable.
 *
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/7/2023
 */
public class Showing
{
    private int identifier; // just a way to distinguish showings
    private Film film;
    private LocalDate date;
    private LocalTime time;
    private Theater theater;
    private ArrayList<Row> rows;
    private HashMap<Booking, Customer> bookings;

    /**
     * Constructor for objects of class Showing.
     * @param film The film of the showing.
     * @param year The year of the showing.
     * @param month The month of the showing.
     * @param day The day of the month of the showing.
     * @param hour The hour of the time of the showing.
     * @param minute The minute of the time of the showing.
     * @param theater The theater in which the film will be shown.
     */
    public Showing(int id, Film film, int year, int month, int day,
                   int hour, int minute, Theater theater) {
        identifier = id;
        this.film = film;
        date = LocalDate.of(year, month, day);
        time = LocalTime.of(hour, minute);
        this.theater = theater;
        rows = new ArrayList<Row>();
        bookings = new HashMap<Booking, Customer>();
        
        int numOfRows = theater.getRows();
        int seatsPerRow = theater.getSeats();
        
        for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++) {
            int firstNum = rowIndex / 26;
            int secondNum = rowIndex % 26;
            char firstLetter = (char)(firstNum + 'A');
            char secondLetter = (char)(secondNum + 'A');
            char[] letters = {firstLetter, secondLetter};
            String rowNum = String.valueOf(letters);
            Row newRow = new Row(rowNum, seatsPerRow);
            rows.add(newRow);
        }
    }
    
    /**
     * Returns the ID number.
     * @return The ID number.
     */
    public int getID() {
        return identifier;
    }
    
    /**
     * Returns the name of the film of the showing.
     * @return The name of the film of the showing.
     */
    public String filmName() {
        return film.toString();
    }

    /**
     * Returns the showing's data, in detail.
     * @return The showing's data, in detail.
     */
    public String showDetail() {
        String filmString = "\"" + film.toString() + "\"\n";
        String theaterString = "Theater number " + theater.getNum() + "\n";
        String dateString = "Date: " + date + "\n";
        String timeString = "Time: " + time + "\n";
        String seatString = "Open seats: " + getOpenSeatNum() + "\n";
        String seatList = getOpenSeatString();
        String returnString = "Showing " + identifier + "\n" + filmString + theaterString +
                                dateString + timeString + seatString + seatList;
        return returnString;
    }
    
    /**
     * Returns the showing's data, concisely.
     * @return The showing's data, concisely.
     */
    public String lessDetail() {
        String filmString = "\"" + film.toString() + "\"\n";
        String theaterString = "    Theater no. " + theater.getNum();
        String dateTime = " at " + getDateTime() + "\n";
        String seatString = "    Open seats: " + getOpenSeatNum();
        String returnString = "Showing " + identifier + ": " +
                                filmString + theaterString + dateTime + seatString;
        return returnString;
    }
    
    /**
     * Returns the showing's detail, minimally, as a string.
     * @return The showing's detail, minimally, as a string.
     */
    public String minDetail() {
        String filmString = "\"" + film.toString() + "\" | ";
        String theaterString = "Theater no. " + theater.getNum();
        String dateTime = " at " + getDateTime();
        String returnString = filmString + theaterString + dateTime;
        return returnString;
    }
    
    /**
     * Returns the film of the showing.
     * @return The film of the showing.
     */
    public Film getFilm() {
        return film;
    }
    
    /**
     * Returns the theater of the showing.
     * @return The theater of the showing.
     */
    public Theater getTheater() {
        return theater;
    }
    
    /**
     * Returns the date of the showing.
     * @return The date of the showing.
     */
    public LocalDate getDate() {
        return date;
    }
    
    /**
     * Returns the time of the showing.
     * @return The time of the showing.
     */
    public LocalTime getTime() {
        return time;
    }
    
    /**
     * Returns the date and time as a string.
     * @return The date and time as a string.
     */
    public String getDateTime() {
        String sDate = date.toString();
        String sTime = time.toString();
        String dateTime = sDate + ", " + sTime;
        return dateTime;
    }
    
    /**
     * Returns a collection of all the seats of the showing.
     * @param The collection of all seats of the showing.
     */
    public ArrayList<Seat> getSeats() {
        ArrayList<Seat> seats = new ArrayList<Seat>();
        for (Row row : rows) {
            ArrayList<Seat> rowSeats = row.getSeats();
            for (Seat seat : rowSeats) {
                seats.add(seat);
            }
        }
        return seats;
    }
    
    /**
     * Returns the collection of bookings.
     * @return The collection of bookings.
     */
    public Set<Booking> getBookings() {
        return bookings.keySet();
    }
    
    /**
     * Returns the customer of a specific booking.
     * @param booking The specific booking.
     * @return The customer of a specific booking.
     */
    public Customer getCust(Booking booking) {
        Customer customer = bookings.get(booking);
        return customer;
    }
    
    /**
     * Returns the collection of all customers who currently have bookings for this showing.
     * @return The collection of all customers who currently have bookings for this showing.
     */
    public Collection<Customer> getAllCust() {
        Collection customers = bookings.values();
        return customers;
    }
    
    /**
     * Returns the number of open seats.
     * @return The number of open seats.
     */
    public int getOpenSeatNum() {
        int openSeats = 0;
        for(Row row : rows) {
            ArrayList<Seat> seats = row.getSeats();
            for (Seat seat : seats) {
                if (seat.isFree()) {
                    openSeats++;
                }
            }
        }
        return openSeats;
    }
    
    /**
     * Returns a list of adjacent seat numbers, given a start and end seat number.
     * If the parameters are out of order, their values are reversed.
     * If they are not in the same row, returns an empty list.
     * @param start First of the adjacent seats.
     * @param end Last of the adjacent seats.
     * @return A list of adjacent seat numbers.
     */
    public ArrayList<String> getAdjacent(String start, String end) {
        ArrayList<String> adjSeats = new ArrayList<String>();
        String startRow = start.substring(0, 2);
        String endRow = end.substring(0, 2);
        Integer startSeat = Integer.valueOf(start.substring(2));
        Integer endSeat = Integer.valueOf(end.substring(2));
        
        if (startRow.equals(endRow)) {
            if (startSeat > endSeat) {
                int startHolder = endSeat;
                int endHolder = startSeat;
                startSeat = startHolder;
                endSeat = endHolder;
            }
            int currentSeat = startSeat;
            while (currentSeat <= endSeat) {
                String seatNum = String.format(startRow + "%02d", currentSeat);
                adjSeats.add(seatNum);
                currentSeat++;
            }
        }
        else {
            System.out.println("Seats entered must be in the same row.");
        }
        
        return adjSeats;
    }
    
    /**
     * Searches for adjacent available seats and returns a list strings,
     * with each string representing a set of available seats.
     * @return A list of strings, each describing a range of available adjacent seats.
     */
    public ArrayList<String> findAdjacent() {
        ArrayList<Seat> seats = getOpenSeats();
        ArrayList<String> groups = new ArrayList<String>();
        ArrayList<String> neighbors = new ArrayList<String>();
        
        for (int index = 0; index < (seats.size() - 1); index++) {
            String currentSeat = seats.get(index).getNum();
            String currentRow = currentSeat.substring(0, 2);
            int currentNum = Integer.valueOf(currentSeat.substring(2));
            String nextSeat = seats.get(index + 1).getNum();
            String nextRow = nextSeat.substring(0, 2);
            int nextNum = Integer.valueOf(nextSeat.substring(2));
            if ((currentRow.equals(nextRow)) && ((currentNum + 1) == nextNum)) {
                neighbors.add(currentSeat);
                if (index == (seats.size() - 1)) {
                    String start = neighbors.get(0);
                    String end = currentSeat;
                    String group = start + " to " + end;
                    groups.add(group);
                    neighbors.clear();
                }
            }
            else if (neighbors.size() > 1) {
                String start = neighbors.get(0);
                String end = currentSeat;
                String group = start + " to " + end;
                groups.add(group);
                neighbors.clear();
            }
            else {
                neighbors.clear();
            }
        }
        return groups;
    }
        
    /**
     * Returns an array list of available seats for the showing.
     * @return An array list of available seats for the showing.
     */
    public ArrayList<Seat> getOpenSeats() {
        ArrayList<Seat> openSeats = new ArrayList<Seat>();
        for(Row row : rows) {
            ArrayList<Seat> seats = row.getSeats();
            for (Seat seat : seats) {
                if (seat.isFree()) {
                    openSeats.add(seat);
                }
            }
        }
        return openSeats;
    }
    
    /**
     * Returns a list of available seats for the showing as a string.
     * @return A list of available seats for the showing as a string.
     */
    public String getOpenSeatString() {
        String seatString = "";
        ArrayList<Seat> openSeats = getOpenSeats();
        for(int index = 0; index < openSeats.size(); index++) {
            seatString += openSeats.get(index).getNum() + " ";
            if (((index + 1) % 10) == 0) {
                seatString += "\n";
            }
        }
        return seatString;
    }
    
    /**
     * Returns a seat, given a seat number.
     * @param number The seat number.
     * @return The seat of the given number; null if no such seat.
     */
    public Seat getSeat(String number) {
        Seat search;
        for(Row row : rows) {
            ArrayList<Seat> seats = row.getSeats();
            for (Seat seat : seats) {
                if (seat.getNum().equals(number)) {
                    return seat;
                }
            }
        }
        return null;
    }
    
    /**
     * Checks if a seat is free.
     * @param The seat number.
     * @return The seat availability. True if free, false if not.
     */
    public boolean seatFree(String number) {
        Seat seat = getSeat(number);
        if (seat.isFree()) {
            return true;
        }
        return false;
    }
    
    /**
     * Add a booking to the showing.
     * @param bookedSeats The list of seats being boooked.
     */
    public void addBooking(ArrayList<Seat> bookedSeats, Customer customer) {
        for (Seat seat : bookedSeats) {
            seat.setUnavailable();
        }
        Booking booking = new Booking(bookedSeats);
        bookings.put(booking, customer);
        customer.addBooking(booking);
    }
    
    /**
     * Cancel a booking, given a list of seats.
     * @param cancelledSeats The list of seats being cancelled.
     */
    public void cancelBooking(ArrayList<Seat> cancelledSeats) {
        Set<Booking> bookSet = bookings.keySet();
        for (Booking booking : bookSet) {
            for (Seat seat : cancelledSeats) {
                if (booking.getSeats().contains(seat)) {
                    Customer customer = bookings.get(booking);
                    customer.removeBooking(booking);
                    bookings.remove(booking);
                }
            }
        }
    }
    
    /**
     * Cancels all bookings.
     */
    public void cancelAll() {
        Set<Booking> bookSet = bookings.keySet();
        for (Booking booking : bookSet) {
            Customer customer = bookings.get(booking);
            customer.removeBooking(booking);
            bookings.remove(booking);
        }
    }
}
