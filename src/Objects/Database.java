package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
    Database System Information
    @author Binary Bros
    @version 1.0
 */

public class Database {
    private static final String databaseURL = "jdbc:mysql://binarybroshotel.c1iem4okk1rj.us-west-1.rds.amazonaws.com:3306/BinaryBrosHotel";
    private static final String databaseUsername = "BinaryBros";
    private static final String databasePassword = "kIRK4Fma744RefBQwwzDAc1F8";

    private static Connection connection = null;

    // Establishes Database Connection
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
            
            System.out.println("Database Connected!");
        }
        return connection;
    }

    // Executes a SQL Query & Returns the Result Set
    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = getConnection();
        
        try (Statement statement = conn.createStatement()) {
            return statement.executeQuery(sql);
        }
    }

    // Closes Database Connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database Connection Closed!");
            } catch (SQLException e) {
                System.err.println("Error Closing Database Connection: " + e.getMessage());
            }
        }
    }
}