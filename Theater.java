/**
 * Objects of this class represent theaters in a given cinema. It describes how
 * many seats exist in the theater, and in how many rows they are organized.
 * 
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/5/2023
 */
public class Theater {

    private int number;
    private int rows;
    private int seatsPerRow;

    /**
     * Constructor for objects of class Theater.
     * @param number The number assigned to the theater to distinguish it from others.
     * @param rows The number of rows of seats in this theater.
     * @param seats The number of seats per row for this theater.
     */
    public Theater(int number, int rows, int seats) {
        this.number = number;
        this.rows = rows;
        this.seatsPerRow = seats;
    }

    /**
     * Returns the theater's assigned number.
     * @return The theater's assigned number.
     */
    public int getNum() {
        return number;
    }

    /**
     * Returns the number of rows in this theater.
     * @return The number of rows in this theater.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of seats per row for this theater.
     * @return The number of seats per row for this theater.
     */
    public int getSeats() {
        return seatsPerRow;
    }

    /**
     * Sets the number of rows in this theater.
     * @param rows The number of rows in this theater.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Sets the number of seats per row for this theater.
     * @param seats The number of seats per row for this theater.
     */
    public void setSeats(int seats) {
        seatsPerRow = seats;
    }
}