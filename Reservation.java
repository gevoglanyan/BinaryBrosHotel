//Need to add more to Reservation

public class Reservation {
    private final int userID;
    private String roomType;
    private boolean booked; // true = taken, false = available
    private User guest;

    public Reservation (int userID, String roomType)
    {
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

    public void isBooked(){
        if(booked == true)
            System.out.println("Room is already booked");
        else{
            System.out.println("Room is available");
    }

    public void confirm(){
        System.out.println("Your reservation is confirmed. Thank you!"); //need to modify to GUI
        sendEmail();
        sendText();
    }
        
}
