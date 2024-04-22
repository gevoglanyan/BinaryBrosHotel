/*
    - Need to Make Better GUI

    - Need to show Reservation (So Canceling is Easier)
    - Need to make Textfield Smaller
*/

package Objects.EditReservationWindow;

import Objects.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 * This class provides a graphical user interface for managing reservations.
 * Users can cancel or modify existing reservations by entering a reservation ID.
 */

public class editReservationWindow extends JFrame {
    private JTextField reservationIDField;
    private JButton cancelButton, modifyButton;

    /**
     * Constructs a new editReservationWindow.
     * Initializes the GUI components and sets up the layout and window properties.
     */

    public editReservationWindow() {
        setTitle("Manage Reservation");
        initializeComponents();
        setUpLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    /**
     * Initializes the components of the window including input fields and buttons.
     * It sets up listeners for the buttons to handle user actions.
     */

    private void initializeComponents() {
        reservationIDField = new JTextField(20);
        cancelButton = new JButton("Cancel");
        modifyButton = new JButton("Modify");

        cancelButton.addActionListener(event -> cancelReservation());
        modifyButton.addActionListener(event -> openModificationWindow());
    }

    /**
     * Sets up the layout of the window, organizing components into panels.
     */

    private void setUpLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel dataPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        dataPanel.add(new JLabel("Reservation ID:"));
        dataPanel.add(reservationIDField);

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(modifyButton);

        mainPanel.add(dataPanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Cancels the reservation with the given reservation ID.
     * Displays a message based on the success or failure of the operation.
     */

    private void cancelReservation() {
        String reservationID = reservationIDField.getText().trim();
        
        if (!reservationID.isEmpty()) {
            try {
                updateReservationStatus(reservationID, "Canceled");
                JOptionPane.showMessageDialog(this, "Reservation Cnceled Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to cancel reservation: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Enter a Valid Reservation ID!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens a window to modify the reservation details for the given reservation ID.
     */

    private void openModificationWindow() {
        String reservationID = reservationIDField.getText().trim();

        if (!reservationID.isEmpty()) {
            new ModificationWindow(this, reservationID).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Enter a Valid Reservation ID!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Updates the reservation status in the database.
     *
     * @param reservationID the ID of the reservation to update
     * @param newStatus the new status to set for the reservation
     * @throws SQLException if there is a database error during update
     */

    private void updateReservationStatus(String reservationID, String newStatus) throws SQLException {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                "UPDATE Reservations SET status = ? WHERE reservationID = ?")) {
                    statement.setString(1, newStatus);
                    statement.setInt(2, Integer.parseInt(reservationID));
                    
                    if (statement.executeUpdate() == 0) {
                        throw new SQLException("Updating Reservation Failed!");
                    }
        }
    }

    /**
     * Inner class to handle modification of reservations.
     */

    class ModificationWindow extends JDialog {
        private JTextField checkInDateField, checkOutDateField;
        private JButton updateButton;
        private String reservationID;

        /**
         * Constructs a ModificationWindow for modifying reservation details.
         *
         * @param owner the Frame from which the dialog is displayed
         * @param reservationID the ID of the reservation to modify
         */

        ModificationWindow(Frame owner, String reservationID) {
            super(owner, "Modify Reservation", true);
            this.reservationID = reservationID;
            initializeWindow();
        }

        /**
         * Initializes the window components and layout.
         */

        private void initializeWindow() {
            checkInDateField = new JTextField(10);
            checkOutDateField = new JTextField(10);
            updateButton = new JButton("Update");

            updateButton.addActionListener(event -> updateReservation());

            setLayout(new FlowLayout());
            add(new JLabel("Check In Date:"));
            add(checkInDateField);
            add(new JLabel("Check Out Date:"));
            add(checkOutDateField);
            add(updateButton);

            setSize(500, 500);
            setLocationRelativeTo(getOwner());
        }

        /**
         * Updates the reservation in the database with new check-in and check-out dates.
         */

        private void updateReservation() {
            String checkInDate = checkInDateField.getText().trim();
            String checkOutDate = checkOutDateField.getText().trim();

            if (!checkInDate.isEmpty() && !checkOutDate.isEmpty()) {
                try (Connection connection = Database.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                        "UPDATE Reservations SET checkInDate = ?, checkOutDate = ? WHERE reservationID = ?")) {
                            statement.setDate(1, java.sql.Date.valueOf(checkInDate));
                            statement.setDate(2, java.sql.Date.valueOf(checkOutDate));
                            statement.setInt(3, Integer.parseInt(reservationID));

                            if (statement.executeUpdate() > 0) {
                                JOptionPane.showMessageDialog(this, "Reservation updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                            } else {
                                throw new SQLException("Updating Reservation Failed!");
                            }
                        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, "Failed to Update Reservation! " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Fill in All Fields.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}                       