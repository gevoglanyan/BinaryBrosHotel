import javax.swing.*;

import Objects.EditReservationWindow.editReservationWindow;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents the main menu window of the Binary Bros Hotel Reservation System.
 * This class provides a user interface for navigating to different reservation management functionalities,
 * such as making or editing reservations, and logging out of the system.
 * 
 * @author Binary Bros
 * @version 1.0
 */

public class mainMenuWindow extends JFrame {
    
    /**
     * Constructor for the mainMenuWindow. Sets up the user interface, including buttons for different actions.
     */

    public mainMenuWindow() {
        setTitle("Binary Bros Hotel Reservation System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));

        JButton buttonMakeReservation = createButton("Make Reservation");
        JButton buttonEditReservation = createButton("Edit Reservation");

        reservationPanel.add(Box.createVerticalGlue()); 
        reservationPanel.add(buttonMakeReservation);
        reservationPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        reservationPanel.add(buttonEditReservation);
        reservationPanel.add(Box.createVerticalGlue()); 

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnLogout = createButton("Log Out");
        
        logoutPanel.add(btnLogout);

        mainPanel.add(reservationPanel, BorderLayout.CENTER);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        add(mainPanel);

        buttonMakeReservation.addActionListener(e -> {
            createReservationWindow reservationWindow = new createReservationWindow();
            reservationWindow.setVisible(true);
        });
        
        buttonEditReservation.addActionListener(e -> {
            editReservationWindow reservationWindow = new editReservationWindow();
            reservationWindow.setVisible(true);
        });
        
        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logging Out");
            System.exit(0);
        });
    }

    /**
     * Creates a stylized JButton with specified text. Buttons in this window have specific fonts,
     * are centered, and change the cursor to a hand when hovered over.
     * 
     * @param text The text to display on the button.
     * @return JButton that is visually and functionally configured for use in the main menu.
     */

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false); 

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
            }
        });

        return button;
    }
}