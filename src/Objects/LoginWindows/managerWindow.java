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
 * @author Binary Bros
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
                
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, roomNumber);
                    stmt.setString(2, roomType);
                    stmt.setString(3, bedType);
                    stmt.setInt(4, maxOccupancy);
                    stmt.setBigDecimal(5, pricePerNight);
                    stmt.setString(6, status);
                    
                    int affectedRows = stmt.executeUpdate();
                    
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(this, "Room Added Successfully!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR Adding Room: " + ex.getMessage());
            }
        }
    }

    /**
     * This class represents a dialog for removing rooms from the hotel management system.
     * It extends JDialog and includes a text field for entering the room number to be removed, and buttons for executing the removal or cancelling the operation.
     */

    class removeRoomWindow extends JDialog {
        private JTextField roomNumberField;
        private JButton removeButton, cancelButton;
    
        public removeRoomWindow(Frame owner) {
            super(owner, "Remove Room", true);
            setLayout(new GridBagLayout());
            
            GridBagConstraints gbc = new GridBagConstraints();
    
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 10, 10, 10);
            
            roomNumberField = new JTextField(20);
            add(new JLabel("Enter Room Number to Remove:"), gbc);
            add(roomNumberField, gbc);
            
            removeButton = new JButton("Remove");
            removeButton.addActionListener(this::removeRoom);
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
            
            JPanel buttonPanel = new JPanel();
            
            buttonPanel.add(removeButton);
            buttonPanel.add(cancelButton);
            add(buttonPanel, gbc);
            
            pack();
            setLocationRelativeTo(owner);
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
                
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, roomNumber);
                    int affectedRows = stmt.executeUpdate();
                    
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(this, "Room Removed Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "No Room Found!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR Removing Room: " + ex.getMessage());
            }
            dispose();
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
                JOptionPane.showMessageDialog(this, "Error Loading Room Data: " + ex.getMessage());
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
    
                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        Object[] row = new Object[]{
                            rs.getInt("reservationID"),
                            rs.getString("fullName"),
                            rs.getString("roomNumber"),
                            rs.getDate("checkInDate"),
                            rs.getDate("checkOutDate"),
                            rs.getString("status")
                        };
                        tableModel.addRow(row);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error Loading Reservation Data: " + ex.getMessage());
            }
        }
    }
}