package Objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Manages user data for the Binary Bros Hotel system. This class handles creating user records,
 * inserting them into the database, and fetching user details based on the username.
 *
 * @author Binary Bros (Diego Arteaga)
 * @date 02/12/2024
 * @version 1.0
 */

public class User {

    /**
     * Represents unique identifier for the user
     */

    private int userID; 

    /**
     * Represents user's full name
     */

    private String fullName;     
    
    /**
     * Represents user's login username
     */

    private String username;
    
    /**
     * Represents user's password for login
     */

    private String password;     
    
    /**
     * Represents user's email address
     */

    private String email;        
    
    /**
     * Represents user's role within the system
     */

    private String role;         

    /**
     * Constructs a User object with complete details and prepares to insert these details into the database.
     *
     * @param userID The unique identifier for the user.
     * @param fullName The full name of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email address of the user.
     * @param role The role of the user in the system.
     */

    public User(int userID, String fullName, String username, String password, String email, String role) throws SQLException {
        this.userID = userID;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    /**
     * Inserts user data into the database then constructs and executes an SQL query to insert the user's details.
     *
     * @throws SQLException If an SQL error occurs during the execution of the insert.
     */

    public void insertIntoDatabase() throws SQLException {
        String sql = "INSERT INTO Users (userID, fullName, username, password, email, role) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, this.userID);
                statement.setString(2, this.fullName);
                statement.setString(3, this.username);
                statement.setString(4, this.password);
                statement.setString(5, this.email);
                statement.setString(6, this.role);

                statement.executeUpdate();
        }
    }

    /**
     * Retrieves a User object from the database based on the username.
     *
     * @param username the username to search for in the database.
     * @return A User object populated with data from the database or null if no such user exists.
     * @throws SQLException If an SQL error occurs during the execution of the query.
     */

    public static User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ?";
        
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                
                ResultSet next = statement.executeQuery();
            
                if (next.next()) {
                    int userID = next.getInt("userID");
                    String fullName = next.getString("fullName");
                    String password = next.getString("password");
                    String email = next.getString("email");
                    String role = next.getString("role");

                    return new User(userID, fullName, username, password, email, role);
            }
            return null;
        }
    }

    /**
     * Returns the username of this user.
     *
     * @return The username as a String.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of this user.
     *
     * @return The password as a String.
     */

    public String getPassword() {
        return password;
    }

    /**
     * Returns the role of this user.
     *
     * @return The role as a String.
     */
    
    public String getRole() {
        return role;
    }
}