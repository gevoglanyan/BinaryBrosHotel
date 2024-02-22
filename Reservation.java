//Need to add more to Reservation

public class Reservation {
    private final int userID;
    private String roomType;

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
}