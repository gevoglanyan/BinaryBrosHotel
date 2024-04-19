package Objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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

      /**
        Adds the reservation and its information to the database
        @param reservationID the reservation ID
        @param roomID the room ID
        @param checkInDate the check-in date
        @param checkOutDate the check-out date
        @param totalPrice the total price
        @param numberOfRooms the number of rooms
        @param paymentMethod the payment method
        @param availableRooms the number of available rooms
        @param roomType the room type
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
        PreparedStatement pstmt = null;

        connection = Database.getConnection();
    
        // Prepare SQL Query
        String sql = "INSERT INTO Reservation (reservationID, roomID, checkInDate, checkOutDate, totalPrice, numberOfRooms, paymentMethod, avaliableRooms, roomType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, reservationID);
        pstmt.setInt(2, roomID);
        pstmt.setString(3, checkInDate);
        pstmt.setString(4, checkOutDate);
        pstmt.setDouble(5, totalPrice);
        pstmt.setInt(6, numberOfRooms);
        pstmt.setInt(7, paymentMethod);
        pstmt.setInt(8, avaliableRooms);
        pstmt.setString(9, roomType);
    
        // Execute the Query
        pstmt.executeUpdate();

        // Need to Add Database
    }

    /**
        Returns the reservationID
    */
    public int reservationID() {
        return reservationID;
    }

    /**
        Returns the roomID
    */
    public int roomID() {
        return roomID;
    }

    /**
        Returns the check-in date
    */
    public String checkInDate() {
        return checkInDate;
    }

    /**
        Returns the check-out date
    */
    public String checkOutDate() {
        return checkOutDate;
    }

    /**
        Returns the total price
    */
    public double totalPrice() {
        return totalPrice;
    }

    /**
        Returns the number of rooms
    */
    public int numberOfRooms() {
        return numberOfRooms;
    }

    /**
        Returns the payment method
    */
    public int paymentMethod() {
        return paymentMethod;
    }

    /**
        Returns the number of available rooms
    */
    public int availableRooms() {
        return availableRooms;
    }

    /**
        Returns the room type
    */
    public String roomType() {
        return roomType;
    }
}
