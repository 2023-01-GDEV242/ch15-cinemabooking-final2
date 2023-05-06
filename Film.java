/**
 * Represents a film being shown at a theater.
 * 
 * @author: Artemis MacDuffie, Ryan Connell, & Tara Nordmann
 * @version: 5/5/2023
 */
public class Film {

    private String name;
    private int lengthInMinutes;

    /**
     * Constructor for creating a Film object.
     * @param name The name of the film.
     * @param lengthInMinutes The length of the film in minutes.
     */
    public Film(String name, int lengthInMinutes) {
        this.name = name;
        this.lengthInMinutes = lengthInMinutes;
    }

    /**
     * Retrieves the name of the film, overriding the toString Object method.
     * @return The name of the film.
     */
    public String toString() {
        return name;
    }

    /**
     * Retrieves the length of the film in minutes.
     * @return The length of the film in minutes.
     */
    public int getLengthInMinutes() {
        return lengthInMinutes;
    }
    
    /**
     * Returns the length of the film in hours and minutes, as a string.
     * @return The length of the film in hours and minutes, as a string.
     */
    public String getLengthHourMin() {
        int hours = lengthInMinutes / 60;
        int minutes = lengthInMinutes % 60;
        String timeString = String.format("%d hours, %d minutes", hours, minutes);
        return timeString;
    }

    /**
     * Sets the name of the film.
     * @param name The name of the film.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the length of the film in minutes.
     * @param lengthInMinutes The length of the film in minutes.
     */
    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    // /**
     // * Returns a string representation of the Film object.
     // * @return A string representation of the Film object.
     // */
    // public String toString() {
        // return "Film [name=" + name + ", lengthInMinutes=" + lengthInMinutes + "]";
    // }
    
     public void displayInfo() {
        System.out.println("Movie Name: " + name);
        System.out.println("Length: " + lengthInMinutes + " minutes");
    }
    
    // public static void main(String[] args) {
        // // Create multiple Movie objects with different names and lengths
        // Film film1 = new Film("The Shawshank Redemption", 142);
        // Film film2 = new Film("The Dark Knight", 152);
        // Film film3 = new Film("The Godfather", 175);

        // // Call displayInfo() on each Movie object to display their information
        // film1.displayInfo();
        // film2.displayInfo();
        // film3.displayInfo();
    // }
}


