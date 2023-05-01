/**
 * Objects of this class represent seats for a given showing.
 * It has availability and an alphanumeric designator.
 * 
 * @author Artemis MacDuffie
 * @version 2023.05.01
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
        number = String.format(letter + "%d", num);
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
     * Change the availability of the seat. If it is available, it
     * will no longer be. If it is unavailable, it will become free.
     */
    public void changeAvailability() {
        available = !available;
    }
}