package Objects.EditReservationWindow;

import Objects.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

/**
 * This class provides a graphical user interface for managing reservations.
 * Users can cancel or modify existing reservations.
 *
 * @author Binary Bros (Alex Pulikkottil)
 * @date 04/21/2024
 * @version 1.0
 */

public class editReservationWindow extends JFrame {
    private JButton cancelButton, modifyButton;
    private JTable reservationsTable;
    private DefaultTableModel tableModel;

    /**
     * Constructs a new editReservationWindow.
     * Initializes the GUI components and sets up the layout and window properties.
     */
    
    public editReservationWindow() {
        setTitle("Manage Reservations");
        initializeComponents();
        setUpLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        populateTable();
    }

    /**
     * Initializes the components of the window including buttons.
     * It sets up listeners for the buttons to handle user actions.
     */

     private void initializeComponents() {
        cancelButton = new JButton("Cancel");
        modifyButton = new JButton("Modify");
        reservationsTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Status", "Check-In", "Check-Out"}, 0);
    
        reservationsTable.setModel(tableModel);
        reservationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
        cancelButton.addActionListener(event -> {
            cancelReservation();
        });
        modifyButton.addActionListener(event -> {
            openModificationWindow();
        });
    }

    /**
     * Sets up the layout of the window, organizing components into panels.
     */

    private void setUpLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JScrollPane tableScrollPane = new JScrollPane(reservationsTable);

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(modifyButton);

        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Populates the table with reservation data from the database.
     */
    private void populateTable() {
        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT reservationID, status, checkInDate, checkOutDate FROM Reservations")) {
            while (resultSet.next()) {
                tableModel.addRow(new Object[]{resultSet.getInt("reservationID"), resultSet.getString("status"),
                                               resultSet.getDate("checkInDate"), resultSet.getDate("checkOutDate")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to Load Reservations: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cancels the reservation with the given reservation ID.
     * Displays a message based on the success or failure of the operation.
     */

     private void cancelReservation() {
        int row = reservationsTable.getSelectedRow();
        if (row >= 0) {
            String reservationID = reservationsTable.getValueAt(row, 0).toString();
    
            SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    updateReservationStatus(reservationID, "Canceled");
                    return true;
                }
    
                @Override
                protected void done() {
                    try {
                        if (get()) {
                            JOptionPane.showMessageDialog(editReservationWindow.this, "Reservation Canceled Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            tableModel.removeRow(row); 
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(editReservationWindow.this, "Failed to Cancel Reservation: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            };
            worker.execute();
        } else {
            JOptionPane.showMessageDialog(this, "Select a Reservation From the Table!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens a window to modify the reservation details for the given reservation ID.
     */

    private void openModificationWindow() {
        int row = reservationsTable.getSelectedRow();
        if (row >= 0) {
            String reservationID = reservationsTable.getValueAt(row, 0).toString();
            new ModificationWindow(this, reservationID).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Select a Reservation From the Table!", "ERROR", JOptionPane.ERROR_MESSAGE);
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