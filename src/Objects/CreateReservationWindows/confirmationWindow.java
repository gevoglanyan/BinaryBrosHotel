import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class provides a window that displays a payment confirmation message to the user.
 * The window is intended to confirm reservation details such as name, room number, bed type,
 * check-in and check-out dates, and the status of the payment.
 * 
 * @author Binary Bros (Diego Arteaga)
 * @date 02/28/2024
 * @version 1.0
 */

 public class confirmationWindow extends JFrame {

    private JLabel confirmationLabel;
    private JButton closeButton;

    /**
     * Constructs a confirmationWindow which displays a payment confirmation message.
     * The window is centered, has a predefined size, and includes a single label
     * that shows the confirmation details passed as a parameter.
     *
     * @param paymentConfirmation A string containing the detailed payment confirmation
     *                            message to be displayed. This message should ideally include
     *                            the reservation name, room number, bed type, check-in and check-out
     *                            dates, and a confirmation of successful payment.
     */
    
     public confirmationWindow(String confirmationMessage) {
        setTitle("Reservation Confirmation");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        confirmationLabel = new JLabel(confirmationMessage, SwingConstants.CENTER);
        add(confirmationLabel, BorderLayout.CENTER);

        closeButton = new JButton("Logout");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}