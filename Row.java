import java.util.ArrayList;

/**
 * Objects of this class represent rows in a theater.
 * It holds a collection of seats.
 * 
 * @author Artemis MacDuffie
 * @version 2023.05.01
 */
public class Row {

    private String letter;
    int numOfSeats;
    ArrayList<Seat> seats;

    /**
     * Constructor for objects of class Row.
     * @param letter The letter designation for this row.
     * @param numOfSeats The number of seats in this row.
     */
    public Row(String letter, int numOfSeats) {
        this.letter = letter;
        this.numOfSeats = numOfSeats;
        seats = new ArrayList<Seat>();

        for (int index = 0; index < numOfSeats; index++) {
            Seat seat = new Seat(letter, index + 1);
            seats.add(seat);
        }
    }

    /**
     * Returns the collection of seats in this row.
     * @return The collection of seats in this row.
     */
    public ArrayList<Seat> getSeats() {
        return seats;
    }
}