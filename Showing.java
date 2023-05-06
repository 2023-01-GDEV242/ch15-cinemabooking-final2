import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Objects of this class represent a given showing at a cinema,
 * described by theater (location) and date/time.
 * Holds a collection of seats that can be marked available or unavailable.
 *
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/5/2023
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
        String seatString = "Open seats: " + "\n" + getOpenSeatNum();
        String returnString = "Showing " + identifier + "\n" +
                        filmString + theaterString + dateString + timeString + seatString;
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
        String seatString = "    Open seats: " + "\n" + getOpenSeatNum();
        String returnString = "Showing " + identifier + ": " +
                                filmString + theaterString + dateTime + seatString;
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
     * Returns the number of open seats.
     * @return The number of open seats.
     */
    public int getOpenSeatNum() {
        int openSeats = 0;
        Iterator<Seat> seatIt = seats.iterator();
        while (seatIt.hasNext()) {
            Seat thisSeat = seatIt.next();
            if (thisSeat.isFree()) {
                openSeats++;
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
            if ((index % 10) == 0) {
                seatString += "\n";
            }
            if (thisSeat.isFree()) {
                seatString += thisSeat.getNum() + " ";
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
