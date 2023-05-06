import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a cinema customer.
 * 
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/5/2023
 */
public class Customer {

    private String name;
    private String phone;
    private ArrayList<Booking> bookings;

    /**
     * Constructor for objects of class Customer.
     * @param name The name of the customer.
     */
    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.bookings = new ArrayList<>();
    }
    
    /**
     * Returns the name of the customer.
     * @return The name of the customer.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the phone number of the customer.
     * @return The phone number of the customer.
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * Sets the phone number.
     * @param The phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns a list of all bookings made by the customer.
     * @return The list of all bookings made by the customer.
     */
    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    
    /**
     * Adds a booking for the customer to their list of bookings.
     * @param booking The booking to be added.
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    /**
     * Removes a booking for the customer to their list of bookings.
     * @param booking The booking to be removed.
     */
    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
}

