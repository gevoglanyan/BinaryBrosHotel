package Objects;

// import java.sql.Connection;
// import java.sql.Statement;
import java.sql.SQLException;

// Reservation's a User Can Make Using Their Account

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

        // Connection databaseConnection = Database.getConnection();
        // Statement myDatabaseStatement = databaseConnection.createStatement();

        // Need to add Database
    }



    /* 
    public Reservation (int userID, String roomType) {
        this.userID = userID;
        this.roomType = roomType;
    }

    public Reservation () {
        // "main" function will call this constructor, so i made stuff up, will fix later.
        this.userID = 0;
        this.roomType = "";
    
    }

    public int getUserId() {
        return userID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void isBooked() {
        if(booked == true) {
            System.out.println("Room is already booked");
        } 
        
        else {
            System.out.println("Room is available");
        }
    }

    public void confirm()  {
        System.out.println("Your reservation is confirmed. Thank you!"); // Need to modify to GUI
        sendEmail();
        sendText();
    }
    */
}