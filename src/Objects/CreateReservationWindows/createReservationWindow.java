import Objects.Database;
import Objects.Email;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class createReservationWindow extends JFrame {
    private JTextField nameField, emailField, checkInDateField, checkOutDateField;
    private JComboBox<String> roomOptions;
    private JButton reserveButton, viewDetailsButton;
    private int currentUserID = 1;

    public createReservationWindow() {
        setTitle("Make a Reservation");
        initializeComponents();
        setUpLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        loadRoomOptions();
    }

    private void initializeComponents() {
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        roomOptions = new JComboBox<>();
        checkInDateField = new JTextField(10);
        checkOutDateField = new JTextField(10);
        reserveButton = new JButton("Reserve");
        viewDetailsButton = new JButton("View Details");

        reserveButton.addActionListener(event -> reserveRoom());
        viewDetailsButton.addActionListener(event -> showRoomDetails());
    }

    private void loadRoomOptions() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT roomNumber, roomType, pricePerNight FROM Rooms WHERE status = 'available'");
            while (rs.next()) {
                String roomDetails = String.format("Room %s: %s - $%.2f per night",
                    rs.getString("roomNumber"), rs.getString("roomType"), rs.getDouble("pricePerNight"));
                roomOptions.addItem(roomDetails);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load rooms: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setUpLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10)); 
        JPanel dataPanel = new JPanel(new GridLayout(6, 2, 5, 5)); 
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); 

        dataPanel.add(new JLabel("Name:"));
        dataPanel.add(nameField);
        dataPanel.add(new JLabel("Email:"));
        dataPanel.add(emailField);
        dataPanel.add(new JLabel("Room Selection:"));
        dataPanel.add(roomOptions);
        dataPanel.add(new JLabel(""));
        dataPanel.add(viewDetailsButton);
        dataPanel.add(new JLabel("Check-in Date (YYYY-MM-DD):"));
        dataPanel.add(checkInDateField);
        dataPanel.add(new JLabel("Check-out Date (YYYY-MM-DD):"));
        dataPanel.add(checkOutDateField);

        // Adding buttons to the button panel
        buttonsPanel.add(reserveButton);

        // Adding panels to the main panel
        mainPanel.add(dataPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // Adding main panel to frame
        add(mainPanel);
    }

    private void showRoomDetails() {
        String selectedRoomDetail = (String) roomOptions.getSelectedItem();
        String selectedRoomNumber = selectedRoomDetail.split(":")[0].replace("Room ", "").trim();
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Rooms WHERE roomNumber = ?")) {
            pstmt.setString(1, selectedRoomNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String details = String.format("Room Number: %s\nType: %s\nBed Type: %s\nMax Occupancy: %d\nPrice per Night: $%.2f\nStatus: %s",
                    rs.getString("roomNumber"), rs.getString("roomType"), rs.getString("bedType"),
                    rs.getInt("maxOccupancy"), rs.getDouble("pricePerNight"), rs.getString("status"));
                JOptionPane.showMessageDialog(this, details, "Room Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load room details: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void reserveRoom() {
        String guestName = nameField.getText();
        String email = emailField.getText();
        Email mail = new Email(email);
        String selectedRoomDetail = (String) roomOptions.getSelectedItem();
        String selectedRoomNumber = selectedRoomDetail.split(":")[0].replace("Room ", "").trim();
        String checkInDate = checkInDateField.getText();
        String checkOutDate = checkOutDateField.getText();

        if (validateInput(guestName, selectedRoomNumber, checkInDate, checkOutDate)) {
            try {
                insertReservation(currentUserID, selectedRoomNumber, checkInDate, checkOutDate);
                String confirmationMessage = "Reservation Confirmed:\n\n" +
                    "Name: " + guestName + "\n" +
                    "Room Number: " + selectedRoomNumber + "\n" +
                    "Check-in Date: " + checkInDate + "\n" +
                    "Check-out Date: " + checkOutDate;
                JOptionPane.showMessageDialog(this, confirmationMessage, "Reservation Confirmation", JOptionPane.INFORMATION_MESSAGE);
                if (!email.isEmpty()) mail.reservationMessage();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Fill in all fields with valid data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insertReservation(int userID, String roomNumber, String checkInDate, String checkOutDate) throws SQLException {
        String sql = "INSERT INTO Reservations (userID, roomNumber, checkInDate, checkOutDate, status) VALUES (?, ?, ?, ?, 'confirmed')";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setString(2, roomNumber);
            pstmt.setDate(3, java.sql.Date.valueOf(checkInDate));
            pstmt.setDate(4, java.sql.Date.valueOf(checkOutDate));
            pstmt.executeUpdate();
        }
    }

    private boolean validateInput(String name, String roomNumber, String checkInDate, String checkOutDate) {
        return !name.isEmpty() && !roomNumber.isEmpty() && isValidDate(checkInDate) && isValidDate(checkOutDate);
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