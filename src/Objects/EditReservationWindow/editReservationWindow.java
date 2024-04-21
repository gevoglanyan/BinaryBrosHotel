package Objects.EditReservationWindow;

import Objects.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class editReservationWindow extends JFrame {
    private JTextField reservationIDField;
    private JButton cancelButton, modifyButton;

    public editReservationWindow() {
        setTitle("Manage Reservation");
        initializeComponents();
        setUpLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        reservationIDField = new JTextField(20);
        cancelButton = new JButton("Cancel");
        modifyButton = new JButton("Modify");

        cancelButton.addActionListener(event -> cancelReservation());
        modifyButton.addActionListener(event -> openModificationDialog());
    }

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

    private void cancelReservation() {
        String reservationID = reservationIDField.getText();
        if (!reservationID.isEmpty()) {
            try {
                updateReservationStatus(reservationID, "Canceled");
                JOptionPane.showMessageDialog(this, "Reservation canceled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to cancel reservation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid reservation ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openModificationDialog() {
        String reservationID = reservationIDField.getText();
        if (!reservationID.isEmpty()) {
            // This is a placeholder for an actual modification window or dialog
            ModificationDialog modificationDialog = new ModificationDialog(this, reservationID);
            modificationDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid reservation ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateReservationStatus(String reservationID, String newStatus) throws SQLException {
        try (Connection connection = Database.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("UPDATE Reservations SET status = ? WHERE reservationID = ?")) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, Integer.parseInt(reservationID));
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating reservation failed, no rows affected.");
            }
        }
    }

    // Nested class for the modification dialog
    class ModificationDialog extends JDialog {
        JTextField checkInDateField, checkOutDateField;
        JButton updateButton;
        String reservationID;

        ModificationDialog(Frame owner, String reservationID) {
            super(owner, "Modify Reservation", true);
            this.reservationID = reservationID;

            checkInDateField = new JTextField(10);
            checkOutDateField = new JTextField(10);
            updateButton = new JButton("Update");

            updateButton.addActionListener(event -> updateReservation());

            setLayout(new FlowLayout());
            add(new JLabel("Check-in Date:"));
            add(checkInDateField);
            add(new JLabel("Check-out Date:"));
            add(checkOutDateField);
            add(updateButton);

            setSize(300, 200);
            setLocationRelativeTo(owner);
        }

        private void updateReservation() {
            String checkInDate = checkInDateField.getText();
            String checkOutDate = checkOutDateField.getText();
            if (!checkInDate.isEmpty() && !checkOutDate.isEmpty()) {
                try (Connection connection = Database.getConnection();
                     PreparedStatement pstmt = connection.prepareStatement(
                             "UPDATE Reservations SET checkInDate = ?, checkOutDate = ? WHERE reservationID = ?")) {
                    pstmt.setDate(1, java.sql.Date.valueOf(checkInDate));
                    pstmt.setDate(2, java.sql.Date.valueOf(checkOutDate));
                    pstmt.setInt(3, Integer.parseInt(this.reservationID));

                    int affectedRows = pstmt.executeUpdate();
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(this, "Reservation updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        throw new SQLException("Updating reservation failed, no rows affected.");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Failed to update reservation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}