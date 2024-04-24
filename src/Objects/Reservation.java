package Objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Represents a reservation within the Binary Bros Hotel system.
 * This class encapsulates all necessary reservation details and is responsible for
 * persisting these details to the database upon creation.
 * 
 * @author Binary Bros (Diego Arteaga)
 * @date 02/12/2024
 * @version 1.0
 */

public class Reservation {

    /**
     * Represents the unique identifier for the reservation
     */

    private final int reservationID; 

    /** 
     * Represents the identifier for the room associated with this reservation
     */

    private final int roomID;    

    /**
     * Represents start date of the reservation
     */

    private final String checkInDate;   

    /**
     * Represents end date of the reservation
     */

    private final String checkOutDate; 
    
    /**
     * Represents total price of the reservation
     */

    private final double totalPrice;   
    
    /**
     * Represents number of rooms in this reservation
     */

    private final int numberOfRooms;  
    
    /**
     * Represents payment method used
     */
    
    private final int paymentMethod; 
    
    /**
     * Represents number of rooms available at time of booking
     */

    private final int availableRooms; 
    
    /**
     * Represents type of room booked
     */

    private final String roomType;     

    /**
     * Constructs a new Reservation object and stores its details to the database.
     *
     * @param reservationID  the reservation's unique identifier.
     * @param roomID  the identifier for the room associated with this reservation.
     * @param checkInDate  the start date of the reservation in YYYY-MM-DD format.
     * @param checkOutDate  the end date of the reservation in YYYY-MM-DD format.
     * @param totalPrice  the total price for the reservation.
     * @param numberOfRooms  the number of rooms booked.
     * @param paymentMethod  the payment method identifier used for the reservation.
     * @param availableRooms  the number of rooms available at the time of booking.
     * @param roomType  the type of room booked.
     * @throws SQLException  If there is a problem executing the SQL to store reservation details.
     */

    public Reservation (int reservationID, int roomID, String checkInDate, String checkOutDate, double totalPrice, 
            int numberOfRooms, int paymentMethod, int avaliableRooms, String roomType) throws SQLException {
        
        this.reservationID = reservationID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.numberOfRooms = numberOfRooms;
        this.paymentMethod = paymentMethod;
        this.availableRooms = avaliableRooms;
        this.roomType = roomType;

        Connection connection = null;
        PreparedStatement statement = null;

        connection = Database.getConnection();
    
        String sql = "INSERT INTO Reservation (reservationID, roomID, checkInDate, checkOutDate, totalPrice, numberOfRooms, paymentMethod, avaliableRooms, roomType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        statement = connection.prepareStatement(sql);

        statement.setInt(1, reservationID);
        statement.setInt(2, roomID);
        statement.setString(3, checkInDate);
        statement.setString(4, checkOutDate);
        statement.setDouble(5, totalPrice);
        statement.setInt(6, numberOfRooms);
        statement.setInt(7, paymentMethod);
        statement.setInt(8, avaliableRooms);
        statement.setString(9, roomType);
    
        statement.executeUpdate();
    }

    /**
     * Gets the unique identifier for this reservation.
     *
     * @return the reservation ID as an integer.
     */

    public int reservationID() {
        return reservationID;
    }

    /**
     * Gets the room ID associated with this reservation.
     *
     * @return the room ID as an integer.
     */

    public int roomID() {
        return roomID;
    }

    /**
     * Gets the check-in date for this reservation.
     *
     * @return the check-in date as a String in YYYY-MM-DD format.
     */

    public String checkInDate() {
        return checkInDate;
    }

    /**
     * Gets the check-out date for this reservation.
     *
     * @return the check-out date as a String in YYYY-MM-DD format.
     */

    public String checkOutDate() {
        return checkOutDate;
    }

    /**
     * Gets the total price for this reservation.
     *
     * @return the total price as a double.
     */

    public double totalPrice() {
        return totalPrice;
    }

    /**
     * Gets the number of rooms booked in this reservation.
     *
     * @return the number of rooms as an integer.
     */

    public int numberOfRooms() {
        return numberOfRooms;
    }

    /**
     * Gets the payment method used for this reservation.
     *
     * @return the payment method as an integer, where each integer corresponds to a specific payment method.
     */

    public int paymentMethod() {
        return paymentMethod;
    }

    /**
     * Gets the number of rooms available at the time this reservation was made.
     *
     * @return the number of available rooms as an integer.
     */

    public int availableRooms() {
        return availableRooms;
    }

    /**
     * Gets the type of room booked in this reservation.
     *
     * @return the room type as a String.
     */
    
    public String roomType() {
        return roomType;
    }
}