package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Provides management for database operations on the Binary Bros Hotel Database.
 * Includes establishing connections, executing queries, and closing the connection.
 * 
 * @author Binary Bros
 * @version 1.0
 */

public class Database {
    private static final String databaseURL = "jdbc:mysql://binarybroshotel.c1iem4okk1rj.us-west-1.rds.amazonaws.com:3306/BinaryBrosHotel";
    private static final String databaseUsername = "BinaryBros";
    private static final String databasePassword = "kIRK4Fma744RefBQwwzDAc1F8";

    private static Connection connection = null;

     /**
     * Retrieves a valid Database connection. Establishes a new one if none exists or if the current one is closed.
     * 
     * @return Connection to the Database.
     * @throws SQLException If a Database Access Error Occurs or the URL is Null.
     */

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
            
            System.out.println("Database Connected!");
        }

        return connection;
    }

    /**
     * Executes a SQL query against the database and returns the result.
     * 
     * @param sql the SQL Query to Execute.
     * @return ResultSet Containing the Results of the Query Execution.
     * @throws SQLException If a Database Access Error Occurs, this method is called on a closed connection or the given SQL statement produces anything other than a single ResultSet.
     */

    public static ResultSet executeQuery(String sql) throws SQLException {
        Connection connection = getConnection();
        
        try (Statement statement = connection.createStatement()) {
            return statement.executeQuery(sql);
        }
    }

    /**
     * Closes the connection to the database
     */
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
