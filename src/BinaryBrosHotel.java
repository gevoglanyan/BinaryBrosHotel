/**
 * Entry point for the Binary Bros Hotel application. This class is responsible for starting the application and initializing the user interface.
 * 
 * This class directly starts the application, prints a status message to the console, and opens the main welcome window of the application.
 * It relies on the welcomeWindow class to create and manage the GUI.
 * 
 * @author Binary Bros
 * @version 1.0
 */

public class BinaryBrosHotel {

    /**
     * The main method that serves as the entry point for the Binary Bros Hotel application.
     * Initializes the application and shows the main welcome window.
     * 
     * @param args Command Line Arguments (not used).
     */
    
    public static void main(String[] args) {
        
        System.out.println("Running!");
        
        welcomeWindow window = new welcomeWindow();
        window.setVisible(true);
    }
}