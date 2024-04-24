import Objects.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import javax.swing.table.DefaultTableModel;

/**
 * A comprehensive manager dashboard window for the Binary Bros Hotel application. 
 * This class provides a GUI interface for administrative tasks such as adding, removing, and viewing rooms.
 * The manager window allows users to manage room details and logout from the dashboard.
 * 
 * @author Binary Bros (Harutyun Gevoglanyan)
 * @date 04/08/2024
 * @version 1.0
 */

public class managerWindow extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton addRoomsButton, removeRoomsButton, viewRoomsButton, viewReservationButton, logoutButton;

    /**
     * Constructor for creating the manager window with initialization of the user interface.
     */

    public managerWindow() {
        setTitle("Binary Bros Hotel Manager Dashboard");

        titleLabel = new JLabel("Manager Dashboard");
        addRoomsButton = new JButton("Add Rooms");
        removeRoomsButton = new JButton("Remove Rooms");
        viewRoomsButton = new JButton("View Rooms");
        viewReservationButton = new JButton("View Reservations");
        logoutButton = new JButton("Logout");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        
        add(titleLabel, gbc);

        gbc.insets = new Insets(5, 50, 5, 50);

        addRoomsButton.addActionListener(this);
        removeRoomsButton.addActionListener(this);
        viewRoomsButton.addActionListener(this);
        viewReservationButton.addActionListener(this);
        logoutButton.addActionListener(this);

        add(addRoomsButton, gbc);
        add(removeRoomsButton, gbc);
        add(viewRoomsButton, gbc);
        add(viewReservationButton, gbc);
        add(logoutButton, gbc);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Handles action events generated by buttons in the manager dashboard.
     * Each button is associated with a specific management task.
     *
     * @param e The ActionEvent to handle.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addRoomsButton) {
            addRoomWindow addRoom = new addRoomWindow(this);
            addRoom.setVisible(true);
        } else if (e.getSource() == removeRoomsButton) {
            removeRoomWindow removeRoom = new removeRoomWindow(this);
            removeRoom.setVisible(true);
        } else if (e.getSource() == viewRoomsButton) {
            viewRoomsWindow viewRooms = new viewRoomsWindow(this);
            viewRooms.setVisible(true);
        } else if (e.getSource() == viewReservationButton) {
            viewReservationWindow viewReservations = new viewReservationWindow(this);
            viewReservations.setVisible(true);
        } else if (e.getSource() == logoutButton) {
            dispose(); 
        }
    }

    /**
     * This class represents a dialog for adding new rooms to the hotel management system.
     * It extends JDialog and includes text fields for entering room properties, combo boxes for selecting room types, bed types, and status, 
     * and buttons for submitting or cancelling the operation.
     */

    class addRoomWindow extends JDialog {
        private JTextField roomNumberField, maxOccupancyField, pricePerNightField;
        private JComboBox<String> roomTypeCombo, bedTypeCombo, statusCombo;

        /**
         * Constructs an AddRoomWindow as a modal dialog that is used to enter details for a new room.
         * The window is initialized with a grid layout to accept user inputs for various room attributes
         * including room number, room type, bed type, maximum occupancy, price per night, and status.
         * It includes an "Add Room" button that triggers the addition of the new room details
         * and a "Cancel" button that closes the dialog.
         *
         * @param owner the parent frame from which the dialog is displayed
         */
    
        public addRoomWindow(Frame owner) {
            super(owner, "Add Room", true);
            setLayout(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
            
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;
    
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(new JLabel("Room Number:"), gbc);
            roomNumberField = new JTextField(20);
            gbc.gridx = 1;
            add(roomNumberField, gbc);
    
            gbc.gridx = 0;
            gbc.gridy = 1;
            add(new JLabel("Room Type:"), gbc);
            roomTypeCombo = new JComboBox<>(new String[]{"Single", "Double", "Queen", "Suite", "Penthouse"});
            gbc.gridx = 1;
            add(roomTypeCombo, gbc);
    
            gbc.gridx = 0;
            gbc.gridy = 2;
            add(new JLabel("Bed Type:"), gbc);
            bedTypeCombo = new JComboBox<>(new String[]{"Twin", "Full", "Queen", "King", "Super King"});
            gbc.gridx = 1;
            add(bedTypeCombo, gbc);
    
            gbc.gridx = 0;
            gbc.gridy = 3;
            add(new JLabel("Max Occupancy:"), gbc);
            maxOccupancyField = new JTextField(20);
            gbc.gridx = 1;
            add(maxOccupancyField, gbc);
    
            gbc.gridx = 0;
            gbc.gridy = 4;
            add(new JLabel("Price Per Night:"), gbc);
            pricePerNightField = new JTextField(20);
            gbc.gridx = 1;
            add(pricePerNightField, gbc);
    
            gbc.gridx = 0;
            gbc.gridy = 5;
            add(new JLabel("Status:"), gbc);
            statusCombo = new JComboBox<>(new String[]{"Available", "Occupied", "Under Maintenance"});
            gbc.gridx = 1;
            add(statusCombo, gbc);
    
            JPanel buttonPanel = new JPanel();
            
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            
            JButton addButton = new JButton("Add Room");
            
            addButton.addActionListener(this::addRoom);
            buttonPanel.add(addButton);
    
            JButton cancelButton = new JButton("Cancel");
            
            cancelButton.addActionListener(e -> dispose());
            buttonPanel.add(cancelButton);
    
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            
            add(buttonPanel, gbc);
    
            pack();
            setLocationRelativeTo(owner);
        }

        /**
         * This method is called when the "Add Room" button is clicked.
         * It collects the room data from the dialog's fields and inserts it into the database.
         * 
         * @param e the event that triggered this method
         */

        private void addRoom(ActionEvent e) {
            String roomNumber = roomNumberField.getText();
            String roomType = (String) roomTypeCombo.getSelectedItem();
            String bedType = (String) bedTypeCombo.getSelectedItem();
            int maxOccupancy = Integer.parseInt(maxOccupancyField.getText());
            BigDecimal pricePerNight = new BigDecimal(pricePerNightField.getText());
            String status = (String) statusCombo.getSelectedItem();

            try (Connection connection = Database.getConnection()) {
                String sql = "INSERT INTO Rooms (roomNumber, roomType, bedType, maxOccupancy, pricePerNight, status) VALUES (?, ?, ?, ?, ?, ?)";
                
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, roomNumber);
                    statement.setString(2, roomType);
                    statement.setString(3, bedType);
                    statement.setInt(4, maxOccupancy);
                    statement.setBigDecimal(5, pricePerNight);
                    statement.setString(6, status);
                    
                    int affectedRows = statement.executeUpdate();
                    
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(this, "Room Added Successfully!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR" + ex.getMessage());
            }
        }
    }

    /**
     * This class represents a dialog for removing rooms from the hotel management system.
     * It extends JDialog and includes a text field for entering the room number to be removed, and buttons for executing the removal or cancelling the operation.
     */

     class removeRoomWindow extends JDialog {
        private JTable roomTable;
        private JTextField roomNumberField;
        private JButton removeButton, cancelButton;
        private DefaultTableModel tableModel;

        /**
         * Constructs a RemoveRoomWindow as a modal dialog that allows the user to remove a room from the system.
         * The window is divided into two main sections: a table displaying existing rooms and an input area
         * for specifying the room number to be removed. It includes a "Remove" button that triggers the deletion
         * of the specified room and a "Cancel" button that closes the dialog.
         * 
         * @param owner the parent frame from which the dialog is displayed
         */
    
        public removeRoomWindow(Frame owner) {
            super(owner, "Remove Room", true);
            setLayout(new BorderLayout());
    
            String[] columnNames = {"Room Number", "Room Type", "Bed Type", "Max Occupancy", "Price Per Night", "Status"};
            tableModel = new DefaultTableModel(columnNames, 0);
            roomTable = new JTable(tableModel);
            loadRoomData();
    
            JScrollPane scrollPane = new JScrollPane(roomTable);
            add(scrollPane, BorderLayout.CENTER);
    
            JPanel inputPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);
    
            roomNumberField = new JTextField(20);
            inputPanel.add(new JLabel("Enter Room Number to Remove:"), gbc);
            inputPanel.add(roomNumberField, gbc);
    
            removeButton = new JButton("Remove");
            removeButton.addActionListener(this::removeRoom);
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
    
            JPanel buttonPanel = new JPanel();
            
            buttonPanel.add(removeButton);
            buttonPanel.add(cancelButton);
            inputPanel.add(buttonPanel, gbc);
    
            add(inputPanel, BorderLayout.SOUTH);
            pack();
            setLocationRelativeTo(owner);
        }

        /**
         * Loads data from the database into the room table model.
         * This method retrieves room details such as room number, type, bed type, maximum occupancy,
         * price per night, and status from the Rooms table in the database. Each room's details are
         * displayed in the room table in the Remove Room window. Errors during data retrieval are caught
         * and displayed to the user through a dialog.
         */
    
        private void loadRoomData() {
            try (Connection connection = Database.getConnection()) {
                String sql = "SELECT roomNumber, roomType, bedType, maxOccupancy, pricePerNight, status FROM Rooms";
                
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        Object[] row = new Object[]{
                            rs.getString("roomNumber"),
                            rs.getString("roomType"),
                            rs.getString("bedType"),
                            rs.getInt("maxOccupancy"),
                            rs.getBigDecimal("pricePerNight"),
                            rs.getString("status")
                        };

                        tableModel.addRow(row);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR" + ex.getMessage());
            }
        }

        /**
         * This method is called when the "Remove" button is clicked.
         * It attempts to remove the specified room from the database.
         * 
         * @param e the event that triggered this method
         */
    
         private void removeRoom(ActionEvent e) {
            String roomNumber = roomNumberField.getText();

            try (Connection connection = Database.getConnection()) {
                String sql = "DELETE FROM Rooms WHERE roomNumber = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, roomNumber);
                    
                    int affectedRows = statement.executeUpdate();
                    
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(this, "Room Removed Successfully!");
                        tableModel.setRowCount(0);
                        loadRoomData();
                    } else {
                        JOptionPane.showMessageDialog(this, "No Room Found!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR" + ex.getMessage());
            }
        }
    }

    /**
     * This class represents a dialog for viewing all rooms in the hotel management system.
     * It extends JDialog and includes a JTable populated with room data, and a button for closing the dialog.
     */

    class viewRoomsWindow extends JDialog {
        private JTable roomTable;
        private JScrollPane scrollPane;

        /**
         * Constructor to create the view rooms dialog.
         * 
         * @param owner the Frame from which the dialog is displayed
         */
    
        public viewRoomsWindow(Frame owner) {
            super(owner, "View Rooms", true);
            setLayout(new BorderLayout());
    
            String[] columnNames = {"Room Number", "Room Type", "Bed Type", "Max Occupancy", "Price Per Night", "Status"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            
            roomTable = new JTable(tableModel);
            scrollPane = new JScrollPane(roomTable);

            roomTable.setFillsViewportHeight(true);
    
            loadRoomData(tableModel);
    
            add(scrollPane, BorderLayout.CENTER);
    
            JButton closeButton = new JButton("Close");
            
            closeButton.addActionListener(e -> dispose());
            add(closeButton, BorderLayout.SOUTH);
    
            setSize(600, 600);
            setLocationRelativeTo(owner);
        }

        /**
         * This method loads the room data from the database and populates the table model associated with the JTable.
         * 
         * @param tableModel the table model to which room data should be added
         */
    
        private void loadRoomData(DefaultTableModel tableModel) {
            try (Connection connection = Database.getConnection()) {
                String sql = "SELECT roomNumber, roomType, bedType, maxOccupancy, pricePerNight, status FROM Rooms";
                
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    ResultSet next = statement.executeQuery();
                    
                    while (next.next()) {
                        Object[] row = new Object[]{
                            next.getString("roomNumber"),
                            next.getString("roomType"),
                            next.getString("bedType"),
                            next.getInt("maxOccupancy"),
                            next.getBigDecimal("pricePerNight"),
                            next.getString("status")
                        };

                        tableModel.addRow(row);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR" + ex.getMessage());
            }
        }
    }

    /**
     * This class represents a dialog window that displays a list of all reservations in the system.
     * It extends {@link JDialog} and includes a {@link JTable} within a {@link JScrollPane} for displaying reservation details.
     */

    class viewReservationWindow extends JDialog {
        private JTable reservationTable;
        private JScrollPane scrollPane;

        /**
         * Constructs a new viewReservationWindow which initializes the GUI components and loads the reservation data into the table.
         * 
         * @param owner the {@link Frame} from which this dialog is displayed
         */
    
        public viewReservationWindow(Frame owner) {
            super(owner, "View Reservations", true);
            setLayout(new BorderLayout());
    
            String[] columnNames = {"Reservation ID", "Guest Name", "Room Number", "Check-in Date", "Check-out Date", "Reservation Status"};
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    
            reservationTable = new JTable(tableModel);
            scrollPane = new JScrollPane(reservationTable);
    
            reservationTable.setFillsViewportHeight(true);
    
            loadReservationData(tableModel);
    
            add(scrollPane, BorderLayout.CENTER);
    
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            add(closeButton, BorderLayout.SOUTH);
    
            setSize(800, 400);
            setLocationRelativeTo(owner);
        }

        /**
         * Loads the reservation data from the database and populates the table model.
         * It executes SQL queries to fetch reservation details and maps them into the table model rows.
         * 
         * @param tableModel the {@link DefaultTableModel} which is used to hold the reservation data displayed in the {@link JTable}
         */
    
        private void loadReservationData(DefaultTableModel tableModel) {
            try (Connection connection = Database.getConnection()) {
                String sql = "SELECT Reservations.reservationID, Accounts.fullName, Rooms.roomNumber, Reservations.checkInDate, Reservations.checkOutDate, Reservations.status " +
                             "FROM Reservations " +
                             "JOIN Accounts ON Reservations.userID = Accounts.id " +
                             "JOIN Rooms ON Reservations.roomID = Rooms.roomID";
    
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    ResultSet next = statement.executeQuery();
                    
                    while (next.next()) {
                        Object[] row = new Object[]{
                            next.getInt("reservationID"),
                            next.getString("fullName"),
                            next.getString("roomNumber"),
                            next.getDate("checkInDate"),
                            next.getDate("checkOutDate"),
                            next.getString("status")
                        };
                        
                        tableModel.addRow(row);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR" + ex.getMessage());
            }
        }
    }
}