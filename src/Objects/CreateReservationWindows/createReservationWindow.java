import Objects.Database;
import Objects.Email;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Provides an interface for creating a reservation in the Binary Bros Hotel system.
 * This class extends JFrame and includes form inputs for reservation details such as guest name,
 * email, room selection, and dates. It also manages database interactions for saving reservations
 * and updating room statuses.
 * 
 * @author Binary Bros (Harutyun Gevoglanyan)
 * @date 02/28/2024
 * @version 1.0
 */

public class createReservationWindow extends JFrame {
    private JTextField nameField, emailField, checkInDateField, checkOutDateField;
    private JComboBox<String> roomOptions;
    private JButton reserveButton, viewDetailsButton;

    private int currentUserID = 1;

    /**
     * Constructor for createReservationWindow that initializes the user interface components,
     * sets up the layout, and loads available room options.
     */

    public createReservationWindow() {
        setTitle("Make a Reservation");
        initializeComponents();
        setUpLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        loadRoomOptions();
    }

    /**
     * Initializes all the UI components required for the reservation form.
     */

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

    /**
     * Loads available room options from the database into the room selection combo box.
     */

    private void loadRoomOptions() {
        try (Connection connection = Database.getConnection();
            Statement statement = connection.createStatement()) {
                ResultSet next = statement.executeQuery("SELECT roomNumber, roomType, pricePerNight FROM Rooms WHERE status = 'available'");
                
                while (next.next()) {
                    String roomDetails = String.format("Room %s: %s - $%.2f per night",
                        next.getString("roomNumber"), next.getString("roomType"), next.getDouble("pricePerNight"));
                    
                        roomOptions.addItem(roomDetails);
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to Load Rooms: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Sets up the layout for the reservation window, arranging components in panels.
     */

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

        buttonsPanel.add(reserveButton);

        mainPanel.add(dataPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Displays details of the selected room.
     */

    private void showRoomDetails() {
        String selectedRoomDetail = (String) roomOptions.getSelectedItem();
        String selectedRoomNumber = selectedRoomDetail.split(":")[0].replace("Room ", "").trim();
        
        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rooms WHERE roomNumber = ?")) {
                statement.setString(1, selectedRoomNumber);
                
                ResultSet next = statement.executeQuery();
                
                if (next.next()) {
                    String details = String.format("Room Number: %s\nType: %s\nBed Type: %s\nMax Occupancy: %d\nPrice per Night: $%.2f\nStatus: %s",
                        next.getString("roomNumber"), next.getString("roomType"), next.getString("bedType"),
                        next.getInt("maxOccupancy"), next.getDouble("pricePerNight"), next.getString("status"));
                    
                        JOptionPane.showMessageDialog(this, details, "Room Details", JOptionPane.INFORMATION_MESSAGE);
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to Load Room Details: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Attempts to reserve the selected room for the guest.
     */
    
     private void reserveRoom() {
        String guestName = nameField.getText();
        String email = emailField.getText();
        String selectedRoomDetail = (String) roomOptions.getSelectedItem();
        String selectedRoomNumber = selectedRoomDetail.split(":")[0].replace("Room ", "").trim();
        String checkInDate = checkInDateField.getText();
        String checkOutDate = checkOutDateField.getText();

        if (validateInput(guestName, selectedRoomNumber, checkInDate, checkOutDate)) {
            try {
                insertReservation(currentUserID, selectedRoomNumber, checkInDate, checkOutDate);
                updateRoomStatus(selectedRoomNumber, "Occupied"); 
                
                /* 
                if (!email.isEmpty()) 
                    new Email(email, "Reservation Confirmation", "Your reservation is confirmed for " + checkInDate + " to " + checkOutDate).send(); 
                */
                
                new paymentWindow(guestName, selectedRoomNumber, "Type fetched from DB or selected item", checkInDate, checkOutDate).setVisible(true);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to Make Reservation: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Fill in All Fields With Valid Data.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens a new payment window.
     */

    /*
    private void openPaymentWindow() {
        paymentWindow paymentWindow = new paymentWindow();
        paymentWindow.setVisible(true);
    }
    */

    /**
     * Updates the status of a specific room in the database.
     *
     * @param roomNumber the number of the room whose status needs to be updated.
     * @param newStatus the new status to set for the room, e.g., "Occupied".
     * @throws SQLException If there is a database access error or the update is not possible.
     */

    private void updateRoomStatus(String roomNumber, String newStatus) throws SQLException {
        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE Rooms SET status = ? WHERE roomNumber = ?")) {
                statement.setString(1, newStatus);
                statement.setString(2, roomNumber);
                statement.executeUpdate();
            }
    }

    /**
     * Inserts a new reservation into the database.
     *
     * @param userID the user ID of the guest making the reservation.
     * @param roomNumber the room number being reserved.
     * @param checkInDate the check-in date for the reservation.
     * @param checkOutDate the check-out date for the reservation.
     * @throws SQLException If there is a database access error or the insert is not executed properly.
     */

    private void insertReservation(int userID, String roomNumber, String checkInDate, String checkOutDate) throws SQLException {
        int roomID = getRoomIDFromRoomNumber(roomNumber);
        
        String sql = "INSERT INTO Reservations (userID, roomID, checkInDate, checkOutDate, status) VALUES (?, ?, ?, ?, 'Confirmed')";
        
        try (Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userID);
                statement.setInt(2, roomID);
                statement.setDate(3, java.sql.Date.valueOf(checkInDate));
                statement.setDate(4, java.sql.Date.valueOf(checkOutDate));
                statement.executeUpdate();
            }
    }

    /**
     * Retrieves the database ID of a room based on its room number.
     *
     * @param roomNumber the room number to look up.
     * @return The database ID of the room, or -1 if the room cannot be found.
     * @throws SQLException If there is a database access error or the query fails.
     */

    private int getRoomIDFromRoomNumber(String roomNumber) throws SQLException {
        int roomID = -1;
        
        try (Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT roomID FROM Rooms WHERE roomNumber = ?")) {
                    statement.setString(1, roomNumber);
                    ResultSet next = statement.executeQuery();
            
                    if (next.next()) {
                        roomID = next.getInt("roomID");
                    }
        }

        return roomID;
    }

    /**
     * Validates the input fields for creating a reservation.
     *
     * @param name the guest's name.
     * @param roomNumber the room number.
     * @param checkInDate the check-in date.
     * @param checkOutDate the check-out date.
     * @return true if all inputs are valid and not empty, false otherwise.
     */

    private boolean validateInput(String name, String roomNumber, String checkInDate, String checkOutDate) {
        return !name.isEmpty() && !roomNumber.isEmpty() && isValidDate(checkInDate) && isValidDate(checkOutDate);
    }

    /**
     * Validates if a string date is in the correct "yyyy-MM-dd" format and is a valid date.
     *
     * @param date the date string to validate.
     * @return true if the date is valid, false otherwise.
     */
    
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