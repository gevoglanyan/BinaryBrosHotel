package Objects;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

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

        Connection databaseConnection = Database.getConnection();
        Statement myDatabaseStatement = databaseConnection.createStatement();

        String mySQL = "INSERT INTO Users (userID, fullName, username, password, email, role) VALUES ('" 
            + this.userID + "', '" + this.fullName + "', '" + this.username + "', '" + this.password 
            + this.email + "', '" + this.role + "')";

        myDatabaseStatement.executeQuery(mySQL);
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
