import Objects.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class createReservationWindow extends JFrame {
    private JTextField nameField, checkInDateField, checkOutDateField;
    private JComboBox<Integer> roomOptions; 
    private JButton reserveButton;
    private int currentUserID = 1; 

    public createReservationWindow() {
        setTitle("Make a Reservation");
        initializeComponents();
        setUpLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        loadRoomOptions();
    }

    private void initializeComponents() {
        nameField = new JTextField(20);
        roomOptions = new JComboBox<>();
        checkInDateField = new JTextField(10);
        checkOutDateField = new JTextField(10);
        reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(event -> reserveRoom());
    }

    private void loadRoomOptions() {
        try (Connection conn = Database.getConnection(); 
                Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT roomID FROM Rooms");
            
                while (rs.next()) {
                    roomOptions.addItem(rs.getInt("roomID"));
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load rooms: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setUpLayout() {
        setLayout(new GridLayout(5, 2));
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Room Selection:"));
        add(roomOptions);
        add(new JLabel("Check-in Date (YYYY-MM-DD):"));
        add(checkInDateField);
        add(new JLabel("Check-out Date (YYYY-MM-DD):"));
        add(checkOutDateField);
        add(new JLabel(""));
        add(reserveButton);
    }

    private void reserveRoom() {
        String guestName = nameField.getText();
        int roomID = (int) roomOptions.getSelectedItem();
        String checkInDate = checkInDateField.getText();
        String checkOutDate = checkOutDateField.getText();

        if (validateInput(guestName, roomID, checkInDate, checkOutDate)) {
            try {
                insertReservation(currentUserID, roomID, checkInDate, checkOutDate);
                String confirmationMessage = "Reservation Confirmed:\n\n" + "Name: " + guestName + "\n" 
                    + "Room ID: " + roomID + "\n" + "Check-in Date: " + checkInDate + "\n" + "Check-out Date: " + checkOutDate;
                JOptionPane.showMessageDialog(this, confirmationMessage, "Reservation Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Fill in all fields with valid data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertReservation(int userID, int roomID, String checkInDate, String checkOutDate) throws SQLException {
        String sql = "INSERT INTO Reservations (userID, roomID, checkInDate, checkOutDate, status) VALUES (?, ?, ?, ?, 'confirmed')";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, roomID);
            pstmt.setDate(3, java.sql.Date.valueOf(checkInDate));  
            pstmt.setDate(4, java.sql.Date.valueOf(checkOutDate));  
            pstmt.executeUpdate();
        }
    }

    private boolean validateInput(String name, int roomID, String checkInDate, String checkOutDate) {
        return !name.isEmpty() && roomID > 0 && isValidDate(checkInDate) && isValidDate(checkOutDate);
    }

    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
            dateTime.setLenient(false);
            dateTime.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}