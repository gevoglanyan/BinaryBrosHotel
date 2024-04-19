package Objects;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
    Stores Guest Reservation Info
    @author Binary Bros
    @version 1.0
 */

public class Reservation {

    /**
        Represents the Reservation ID Number
    */
    private final int reservationID;
    /**
        Represents the Room's ID Number
    */
    private final int roomID;
    /**
        Represents the Reservation Check In Date
    */
    private final String checkInDate;
    /**
        Represents the Reservation Check Out Date
    */
    private final String checkOutDate;
    /**
        Represents the Reservation's Total Price
    */
    private final double totalPrice;
    /**
        Represents the Number of Rooms
    */
    private final int numberOfRooms;
    /**
        Represents the Payment Method
    */
    private final int paymentMethod;
    /**
        Represents the Number of Available Rooms
    */
    private final int availableRooms;
    /**
        Represents the Room Type
    */
    private final String roomType;

      /**
        Adds the Reservation and Information to Database
        @param reservationID the Reservation ID
        @param roomID the Room ID
        @param checkInDate the Check In Date
        @param checkOutDate the Check Out Date
        @param totalPrice the Total Price
        @param numberOfRooms the Number of Rooms
        @param paymentMethod the Payment Method
        @param availableRooms the Number of Available Rooms
        @param roomType the Room Type
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
    
        pstmt.executeUpdate();
    }

    /**
        Returns reservationID
    */
    public int reservationID() {
        return reservationID;
    }

    /**
        Returns roomID
    */
    public int roomID() {
        return roomID;
    }

    /**
        Returns checkInDate
    */
    public String checkInDate() {
        return checkInDate;
    }

    /**
        Returns checkOutDate
    */
    public String checkOutDate() {
        return checkOutDate;
    }

    /**
        Returns totalPrice
    */
    public double totalPrice() {
        return totalPrice;
    }

    /**
        Returns numberOfRooms
    */
    public int numberOfRooms() {
        return numberOfRooms;
    }

    /**
        Returns paymentMethod
    */
    public int paymentMethod() {
        return paymentMethod;
    }

    /**
        Returns availableRooms
    */
    public int availableRooms() {
        return availableRooms;
    }

    /**
        Returns roomType
    */
    public String roomType() {
        return roomType;
    }
}
