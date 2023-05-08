import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Set;

/**
 * The test class CinemaBookingSystemTest.
 *
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/7/2023
 */
public class CinemaBookingSystemTest
{
    CinemaBookingSystem cinema;
    Customer bob;
    Film film;
    Theater theater;
    Showing show;
    
    /**
     * Default constructor for test class CinemaBookingSystemTest
     */
    public CinemaBookingSystemTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        cinema = new CinemaBookingSystem(3, 6, 6);
        bob = new Customer("Bob", "908 555 1111");
        film = new Film("Cutting Edge", 132);
        theater = new Theater(4, 6, 6);
        show = new Showing(1, film, 2023, 5, 14, 14, 30, theater);
        cinema.addCustomer(bob);
        cinema.addTheater(theater);
        cinema.addShow(show);
        cinema.addFilm(film);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testOneBook()
    {
        cinema.createBooking(show, "AC03", bob);
        ArrayList<Seat> openSeat = show.getOpenSeats();
        assertEquals(35, openSeat.size());
        ArrayList<Booking> bobBook = bob.getBookings();
        assertEquals(1, bobBook.size());
        ArrayList<Seat> bookSeats = bobBook.get(0).getSeats();
        assertEquals(1, bookSeats.size());
        Set<Booking> showBook = show.getBookings();
        assertEquals(1, showBook.size());
        Booking[] showBookArray = showBook.toArray(new Booking[1]);
        ArrayList<Seat> showSeats = showBookArray[0].getSeats();
        assertEquals(1, showSeats.size());
    }

    @Test
    public void testMultiBook()
    {
        ArrayList<String> seatNumList = new ArrayList<String>();
        seatNumList.add("AC02");
        seatNumList.add("AC03");
        seatNumList.add("AC04");
        cinema.multiBooking(show, seatNumList, bob);
        ArrayList<Seat> openSeat = show.getOpenSeats();
        assertEquals(33, openSeat.size());
        ArrayList<Booking> bobBook = bob.getBookings();
        assertEquals(1, bobBook.size());
        ArrayList<Seat> bookSeats = bobBook.get(0).getSeats();
        assertEquals(3, bookSeats.size());
        Set<Booking> showBook = show.getBookings();
        assertEquals(1, showBook.size());
        Booking[] showBookArray = showBook.toArray(new Booking[1]);
        ArrayList<Seat> showSeats = showBookArray[0].getSeats();
        assertEquals(3, showSeats.size());
    }
    
    @Test
    public void testSeatStatus() {
        ArrayList<String> seatNumList = new ArrayList<String>();
        seatNumList.add("AC02");
        seatNumList.add("AC03");
        seatNumList.add("AC04");
        cinema.multiBooking(show, seatNumList, bob);
        String freeSeatNum1 = "AC01";
        String freeSeatNum2 = "AC05";
        String takenSeatNum1 = "AC02";
        String takenSeatNum2 = "AC04";
        assertEquals(true, show.seatFree(freeSeatNum1));
        assertEquals(true, show.seatFree(freeSeatNum2));
        assertEquals(false, show.seatFree(takenSeatNum1));
        assertEquals(false, show.seatFree(takenSeatNum2));
        Seat freeSeat1 = show.getSeat(freeSeatNum1);
        Seat freeSeat2 = show.getSeat(freeSeatNum2);
        Seat takenSeat1 = show.getSeat(takenSeatNum1);
        Seat takenSeat2 = show.getSeat(takenSeatNum2);
        assertEquals(true, freeSeat1.isFree());
        assertEquals(true, freeSeat2.isFree());
        assertEquals(false, takenSeat1.isFree());
        assertEquals(false, takenSeat2.isFree());
    }
    
    @Test
    public void testSeatChangeStatus() {
        ArrayList<String> seatNumList = new ArrayList<String>();
        seatNumList.add("AC02");
        seatNumList.add("AC03");
        seatNumList.add("AC04");
        cinema.multiBooking(show, seatNumList, bob);
        String freeSeatNum1 = "AC01";
        String freeSeatNum2 = "AC05";
        String takenSeatNum1 = "AC02";
        String takenSeatNum2 = "AC04";
        Seat freeSeat1 = show.getSeat(freeSeatNum1);
        Seat freeSeat2 = show.getSeat(freeSeatNum2);
        Seat takenSeat1 = show.getSeat(takenSeatNum1);
        Seat takenSeat2 = show.getSeat(takenSeatNum2);
        freeSeat1.setAvailable();
        freeSeat2.setAvailable();
        takenSeat1.setAvailable();
        takenSeat2.setAvailable();
        assertEquals(true, freeSeat1.isFree());
        assertEquals(true, freeSeat2.isFree());
        assertEquals(true, takenSeat1.isFree());
        assertEquals(true, takenSeat2.isFree());
        freeSeat1.setUnavailable();
        freeSeat2.setUnavailable();
        takenSeat1.setUnavailable();
        takenSeat2.setUnavailable();
        assertEquals(false, freeSeat1.isFree());
        assertEquals(false, freeSeat2.isFree());
        assertEquals(false, takenSeat1.isFree());
        assertEquals(false, takenSeat2.isFree());
    }
}



