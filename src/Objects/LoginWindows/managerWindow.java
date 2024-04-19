import Objects.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import javax.swing.table.DefaultTableModel;

public class managerWindow extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton addRoomsButton, removeRoomsButton, viewRoomsButton, logoutButton;

    public managerWindow() {
        setTitle("Binary Bros Hotel Manager Dashboard");

        titleLabel = new JLabel("Manager Dashboard");
        addRoomsButton = new JButton("Add Rooms");
        removeRoomsButton = new JButton("Remove Rooms");
        viewRoomsButton = new JButton("View Rooms");
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
        logoutButton.addActionListener(this);

        add(addRoomsButton, gbc);
        add(removeRoomsButton, gbc);
        add(viewRoomsButton, gbc);
        add(logoutButton, gbc);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Runs the Different Buttons

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
        } else if (e.getSource() == logoutButton) {
            dispose(); 
        }
    }

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

        private void addRoom(ActionEvent e) {
            String roomNumber = roomNumberField.getText();
            String roomType = (String) roomTypeCombo.getSelectedItem();
            String bedType = (String) bedTypeCombo.getSelectedItem();
            int maxOccupancy = Integer.parseInt(maxOccupancyField.getText());
            BigDecimal pricePerNight = new BigDecimal(pricePerNightField.getText());
            String status = (String) statusCombo.getSelectedItem();

            try (Connection conn = Database.getConnection()) {
                String sql = "INSERT INTO Rooms (roomNumber, roomType, bedType, maxOccupancy, pricePerNight, status) VALUES (?, ?, ?, ?, ?, ?)";
                
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, roomNumber);
                    stmt.setString(2, roomType);
                    stmt.setString(3, bedType);
                    stmt.setInt(4, maxOccupancy);
                    stmt.setBigDecimal(5, pricePerNight);
                    stmt.setString(6, status);
                    
                    int affectedRows = stmt.executeUpdate();
                    
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(this, "Room added successfully!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding the room: " + ex.getMessage());
            }
        }
    }

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
    
        private void removeRoom(ActionEvent e) {
            String roomNumber = roomNumberField.getText();
            
            try (Connection conn = Database.getConnection()) {
                String sql = "DELETE FROM Rooms WHERE roomNumber = ?";
                
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, roomNumber);
                    int affectedRows = stmt.executeUpdate();
                    
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(this, "Room removed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(this, "No room found with that number.");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error removing the room: " + ex.getMessage());
            }
            dispose();
        }
    }

    class viewRoomsWindow extends JDialog {
        private JTable roomTable;
        private JScrollPane scrollPane;
    
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
    
            setSize(500, 500);
            setLocationRelativeTo(owner);
        }
    
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
}