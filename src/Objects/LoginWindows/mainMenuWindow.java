import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
    Shows User The Binary Bros Hotel Menu
    @author Binary Bros
    @version 1.0
 */

public class mainMenuWindow extends JFrame {
    public mainMenuWindow() {
        setTitle("Binary Bros Hotel Reservation System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));

        JButton btnMakeReservation = createButton("Make Reservation");
        JButton btnEditReservation = createButton("Edit Reservation");

        reservationPanel.add(Box.createVerticalGlue()); 
        reservationPanel.add(btnMakeReservation);
        reservationPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        reservationPanel.add(btnEditReservation);
        reservationPanel.add(Box.createVerticalGlue()); 

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnLogout = createButton("Log Out");
        
        logoutPanel.add(btnLogout);

        mainPanel.add(reservationPanel, BorderLayout.CENTER);
        mainPanel.add(logoutPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnMakeReservation.addActionListener(e -> {
            createReservationWindow reservationWindow = new createReservationWindow();
            reservationWindow.setVisible(true);
        });
        
        btnEditReservation.addActionListener(e -> JOptionPane.showMessageDialog(null, "Opening Edit Reservation Window"));

        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Logging Out");
            System.exit(0);
        });
    }

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