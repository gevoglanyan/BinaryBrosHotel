package Objects;

// import java.sql.Connection;
// import java.sql.Statement;
import java.sql.SQLException;

// Account the User Use's When Using the Application
// Might Need to Make Seperate Class for Manager's

public class User {

    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) throws SQLException {

        this.username = username;
        this.password = password;
        this.role = role;

        // Connection databaseConnection = Database.getConnection();
        // Statement myDatabaseStatement = databaseConnection.createStatement();

        // Need to add Database
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