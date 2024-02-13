//Need to add more to Reservation

public class Reservation {
    private final int userID;
    private String roomType;

    public Reservation (int userID, String roomType)
    {
        this.userID = userID;
        this.roomType = roomType;
    }

    public int getUserId() {
        return userID;
    }

    public String getRoomType() {
        return roomType;
    }
}