package Objects;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
    Stores user reservation info into Database
    @author Binary Bros
    @version 1.0
 */

public class Reservation {

    /**
        Represents the reservation ID number
    */
    private final int reservationID;
    /**
        Represents the room's ID number
    */
    private final int roomID;
    /**
        Represents the reservation check-in date
    */
    private final String checkInDate;
    /**
        Represents the reservation check-out date
    */
    private final String checkOutDate;
    /**
        Represents the reservation's total price
    */
    private final double totalPrice;
    /**
        Represents the number of rooms
    */
    private final int numberOfRooms;
    /**
        Represents the paymentMethod
    */
    private final int paymentMethod;
    /**
        Represents the number of available rooms
    */
    private final int availableRooms;
    /**
        Represents the room type
    */
    private final String roomType;

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

        Connection databaseConnection = Database.getConnection();
        Statement myDatabaseStatement = databaseConnection.createStatement();

        // Need to Add Database
    }

    public int reservationID() {
        return reservationID;
    }

    public int roomID() {
        return roomID;
    }

    public String checkInDate() {
        return checkInDate;
    }

    public String checkOutDate() {
        return checkOutDate;
    }

    public double totalPrice() {
        return totalPrice;
    }

    public int numberOfRooms() {
        return numberOfRooms;
    }

    public int paymentMethod() {
        return paymentMethod;
    }

    public int availableRooms() {
        return availableRooms;
    }

    public String roomType() {
        return roomType;
    }
}