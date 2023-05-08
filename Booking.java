import java.util.ArrayList;

/**
 * Represents a booking for a particular showing and its seats.
 * 
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/7/2023
 */
public class Booking {
    
    private ArrayList<Seat> seats;
    
    /**
     * Creates a new Booking object with the given showing and seat.
     * @param seat the seat associated with the booking
     */
    public Booking(ArrayList<Seat> seats) {
        this.seats = seats;
        for (Seat seat : seats) {
            seat.setUnavailable();
        }
    }

    /**
     * Gets the seat associated with the booking.
     * @return the Seat object for the booking
     */
    public ArrayList<Seat> getSeats() {
        return seats;
    }
    
    /**
     * 
     */
    public void cancel() {
        for (Seat seat : seats) {
            seat.setAvailable();
        }
        seats.clear();
    }
    
    // /**
     // * Returns a string of detailed information regarding the booking.
     // * @return A string of detailed information regarding the booking.
     // */
    // public String getInfo() {
        // String film = showing.getFilm().toString();
        // String dateTime = showing.getDateTime();
        // int theater = showing.getTheater().getNum();
        // String seatNum = seat.getNum();
        // String info = "\"" + film + "\"" + dateTime +
            // "\nTheater" + theater + ", seat " + seatNum;
        // return info;
    // }

    // /**
     // * Changes the status of the booking to the given value.
     // * @param status the new status of the booking
     // */
    // public void setStatus(String status) {
        // this.status = status;
    // }
}


