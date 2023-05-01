
/**
 * Represents a booking for a particular showing and seat.
 * @author Final2 group
 */
public class Booking {
    private String status;
    private Seat seat;
    private Showing showing;

    /**
     * Creates a new Booking object with the given showing and seat, with an initial status of "Booked".
     * @param showing the showing associated with the booking
     * @param seat the seat associated with the booking
     */
    public Booking(Showing showing, Seat seat) {
        this.showing = showing;
        this.seat = seat;
        this.status = "Booked";
    }

    /**
     * Gets the current status of the booking.
     * @return the booking status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the seat associated with the booking.
     * @return the Seat object for the booking
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Gets the showing associated with the booking.
     * @return the Showing object for the booking
     */
    public Showing getShowing() {
        return showing;
    }

    /**
     * Changes the status of the booking to the given value.
     * @param status the new status of the booking
     */
    public void setStatus(String status) {
        this.status = status;
    }
}


