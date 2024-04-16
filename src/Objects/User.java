package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
    Takes user info and stores it into Database
    @author Binary Bros
    @version 1.0
 */

public class User {
    /**
        Represents user's unique ID number
    */
    private int userID;
    /**
        Represents user's full name
    */
    private String fullName;
    /**
        Represents user's username
    */
    private String username;
    /**
        Represents user's password
    */
    private String password;
    /**
        Represents user's email address
    */
    private String email;
    /**
        Represents the user's role
    */
    private String role;

    /**
        Adds the user and all of their information into the user database
        @param userID the userID
        @param fullname the full name
        @param username the username
        @param password the password
        @param email the email
        @param role the role
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
        Gets the user's username
    */
    public String getUsername() {
        return username;
    }

    /**
        Gets the user's password
    */
    public String getPassword() {
        return password;
    }

    /**
        Gets the user's role
    */
    public String getRole() {
        return role;
    }
}