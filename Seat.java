/**
 * Objects of this class represent seats for a given showing.
 * It has availability and an alphanumeric designator.
 * 
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/5/2023
 */
public class Seat {

    private boolean available;
    private String number;

    /**
     * Constructor for objects of class Seat.
     * @param letter The alphabetic component of the seat number.
     * @param num The numeric component of the seat number.
     */
    public Seat(String letter, int num) {
        number = String.format(letter + "%02d", num);
        available = true;
    }

    /**
     * Returns the seat number, which is made of the row letters and number within that row.
     * @return The seat number.
     */
    public String getNum() {
        return number;
    }

    /**
     * Returns the availability for the seat.
     * @return The availability for the seat.
     */
    public boolean isFree() {
        return available;
    }

    /**
     * Set the seat to unavailable.
     */
    public void setUnavailable() {
        available = false;
    }
    
    /**
     * Set the seat to available.
     */
    public void setAvailable() {
        available = true;
    }
}