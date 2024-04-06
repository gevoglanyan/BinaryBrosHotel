package Objects;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
    Stores User Reservation Info into Database
    @author Binary Bros
    @version 1.0
 */

public class Reservation {

    private final int reservationID;
    private final int roomID;
    private final String checkInDate;
    private final String checkOutDate;
    private final double totalPrice;
    private final int numberOfRooms;
    private final int paymentMethod;
    private final int availableRooms;
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