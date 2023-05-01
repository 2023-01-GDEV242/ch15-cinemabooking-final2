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
    private String film; // private Film film;
    private LocalDate date;
    private LocalTime time;
    private Theater theater;
    private ArrayList<Seat> seats;

    /**
     * Constructor for objects of class Showing.
     */
    public Showing(String film, int year, int month, int day, int hour, int minute, Theater theater) {
        this.film = film;
        date = LocalDate.of(year, month, day);
        time = LocalTime.of(hour, minute);
        this.theater = theater;
        seats = new ArrayList<Seat>();
        
        int rows = theater.getRows();
        int seatsPerRow = theater.getSeats();
        
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            int firstNum = rowIndex / 26;
            int secondNum = rowIndex - (firstNum * 26);
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
     * 
     */
    public void printData() {
        System.out.println("Theater number " + theater.getNumber());
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.print("Open seats: ");
        for (int index = 0; index < seats.size(); index++) {
            
        }
    }
}
