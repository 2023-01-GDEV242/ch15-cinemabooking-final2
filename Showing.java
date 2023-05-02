import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Objects of this class represent a given showing at a cinema,
 * described by theater (location) and date/time.
 * Holds a collection of seats that can be marked available or unavailable.
 *
 * @author Artemis MacDuffie
 * @version 2023.05.01
 */
public class Showing
{
    private int identifier; // just a way to distinguish showings
    private Film film;
    private LocalDate date;
    private LocalTime time;
    private Theater theater;
    private ArrayList<Seat> seats;

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
        seats = new ArrayList<Seat>();
        
        int rows = theater.getRows();
        int seatsPerRow = theater.getSeats();
        
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            int firstNum = rowIndex / 26;
            int secondNum = rowIndex % 26;
            char firstLetter = (char)(firstNum + 'A');
            char secondLetter = (char)(secondNum + 'A');
            char[] letters = {firstLetter, secondLetter};
            String rowNum = String.valueOf(letters);
            Row newRow = new Row(rowNum, seatsPerRow);
            Iterator<Seat> newSeats = newRow.getSeats().iterator();
            while (newSeats.hasNext()) {
                seats.add(newSeats.next());
            }
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
     * Converts the data in the object to a string.
     * @return A string containing the object's data.
     */
    public String toString() {
        String filmString = film.toString() + "\n";
        String theaterString = "Theater number " + theater.getNumber() + "\n";
        String dateString = "Date: " + date + "\n";
        String timeString = "Time: " + time + "\n";
        String seatString = "Open seats: " + "\n" + String.valueOf(getOpenSeatNum());
        String returnString = filmString + theaterString + dateString + timeString + seatString;
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
     * Returns the number of open seats.
     * @return The number of open seats.
     */
    public int getOpenSeatNum() {
        int openSeats = 0;
        for (int index = 0; index < seats.size(); index++) {
            if (seats.get(index).isFree()) {
                openSeats++;
            }
        }
        return openSeats;
    }
    
    /**
     * Searches for adjacent available seats and returns a list strings,
     * with each string representing a set of available seats.
     * @return A list of strings, each describing a range of available adjacent seats.
     */
    public ArrayList<String> findAdjacent() {
        // test code
        String list1 = "AB04 to AB07";
        String list2 = "AD09 to AD13";
        String list3 = "AG02 to AG09";
        ArrayList<String> groups = new ArrayList<String>();
        groups.add(list1);
        groups.add(list2);
        groups.add(list3);
        return groups;
    }
    
    /**
     * Returns an array list of available seats for the showing.
     * @return An array list of available seats for the showing.
     */
    public ArrayList<Seat> getOpenSeats() {
        ArrayList<Seat> openSeats = new ArrayList<Seat>();
        Iterator<Seat> seatIt = seats.iterator();
        while (seatIt.hasNext()) {
            Seat thisSeat = seatIt.next();
            if (thisSeat.isFree()) {
                openSeats.add(thisSeat);
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
        int index = 0;
        Iterator<Seat> seatIt = seats.iterator();
        while (seatIt.hasNext()) {
            Seat thisSeat = seatIt.next();
            index++;
            if (thisSeat.isFree()) {
                seatString += thisSeat.getNum() + " ";
            }
            if ((index % 10) == 0) {
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
        for (int index = 0; index < seats.size(); index++) {
            if (number == seats.get(index).getNum()) {
                return seats.get(index);
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
}
