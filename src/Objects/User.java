package Objects;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
    Takes User Info and Stores Info into Database
    @author Binary Bros
    @version 1.0
 */

public class User {
    private int userID;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String role;

    public User(int userID, String fullName, String username, String password, String email, String role) throws SQLException {

        this.userID = userID;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;

        Connection databaseConnection = Database.getConnection();
        Statement myDatabaseStatement = databaseConnection.createStatement();

        String mySQL = "INSERT INTO Users (userID, fullName, username, password, email, role) VALUES ('" 
            + this.userID + "', '" + this.fullName + "', '" + this.username + "', '" + this.password 
            + this.email + "', '" + this.role + "')";

        myDatabaseStatement.executeQuery(mySQL);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}