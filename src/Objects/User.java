package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
    Takes User Info and Stores it into Database
    @author Binary Bros
    @version 1.0
 */

public class User {
    /**
        Represents User's Unique ID Number
    */
    private int userID;
    /**
        Represents User's Full Name
    */
    private String fullName;
    /**
        Represents User's Username
    */
    private String username;
    /**
        Represents User's Password
    */
    private String password;
    /**
        Represents User's Email Address
    */
    private String email;
    /**
        Represents the User's Role
    */
    private String role;

    /**
        Adds the User and All of Their Information into Database
        @param userID the User ID
        @param fullname the Full Name
        @param username the Username
        @param password the Password
        @param email the Email
        @param role the Role
    */
    public User(int userID, String fullName, String username, String password, String email, String role) throws SQLException {
        this.userID = userID;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public void insertIntoDatabase() throws SQLException {
        String sql = "INSERT INTO Users (userID, fullName, username, password, email, role) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.userID);
            pstmt.setString(2, this.fullName);
            pstmt.setString(3, this.username);
            pstmt.setString(4, this.password);
            pstmt.setString(5, this.email);
            pstmt.setString(6, this.role);
            pstmt.executeUpdate();
        }
    }

    public static User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
                
                ResultSet rs = pstmt.executeQuery();
            
                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String fullName = rs.getString("fullName");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String role = rs.getString("role");

                    return new User(userID, fullName, username, password, email, role);
            }
            return null;
        }
    }

    /**
        Gets the User's Username
    */
    public String getUsername() {
        return username;
    }

    /**
        Gets the User's Password
    */
    public String getPassword() {
        return password;
    }

    /**
        Gets the User's Role
    */
    public String getRole() {
        return role;
    }
}