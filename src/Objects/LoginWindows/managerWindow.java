//package Objects.LoginWindows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
    Allows Managers Functionality
    @author Binary Bros
    @version 1.0
 */

public class managerWindow extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton addRoomsButton, removeRoomsButton, viewRoomsButton, logoutButton;

    public managerWindow() {
        setTitle("Manager Dashboard");

        titleLabel = new JLabel("Binary Bros Hotel Manager Dashboard");
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

        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addRoomsButton) {
            JOptionPane.showMessageDialog(this, "Add Rooms Button Clicked");
        } else if (e.getSource() == removeRoomsButton) {
            JOptionPane.showMessageDialog(this, "Remove Rooms Button Clicked");
        } else if (e.getSource() == viewRoomsButton) {
            JOptionPane.showMessageDialog(this, "View Rooms Button Clicked");
        } else if (e.getSource() == logoutButton) {
            dispose(); 
        }
    }

    // Test Purposes
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new managerWindow());
    }
}