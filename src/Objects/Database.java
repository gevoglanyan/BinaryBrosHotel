/*
// If we end up using Database

import java.sql.*;

// Connects to Database and Allows for Other Classes to Use getConnection()

public class Database {

    static Connection databaseConnection = null;
    static String databaseURL = "jdbc:mysql://localhost:3306/BinaryBrosHotel";
    static String databaseUsername = "root";
    static String databasePassword = "uFqkP$FJ4l*VMnt";

    public static Connection getConnection() {
        
        if(databaseConnection == null) {
            try {
                databaseConnection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

                if(databaseConnection != null) {
                    System.out.println("Database Connected");
                }
            } 
            
            catch (SQLException checkError) {

                // Handle's Any Errors
                System.out.println("SQLException: " + checkError.getMessage());
                System.out.println("SQLState: " + checkError.getSQLState());
                System.out.println("Vendor Error: " + checkError.getErrorCode());
            }
        }

        return databaseConnection;
    }

    public Connection getConnector() {

        if (databaseConnection == null) {
            try {
                databaseConnection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
                     
                if (databaseConnection != null) {
                    System.out.println("Database Connected!");
                }
            } 
            
            catch (SQLException checkError) {

                // Handle's Any Errors
                System.out.println("SQLException: " + checkError.getMessage());
                System.out.println("SQLState: " + checkError.getSQLState());
                System.out.println("Vendor Error: " + checkError.getErrorCode());
            }
        }

        return databaseConnection;
    }

    public static ResultSet getResultSet(String sql) throws SQLException {

        Connection databaseConnection = Database.getConnection();
        Statement myStatement = databaseConnection.createStatement();
        
        return myStatement.executeQuery(sql);
    }
}

*/