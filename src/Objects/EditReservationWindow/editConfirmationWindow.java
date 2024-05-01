package Objects.EditReservationWindow;

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

public class editConfirmationWindow extends JFrame {
    private JLabel confirmationLabel;
    private JButton closeButton;

    /**
     * Constructs a confirmationWindow which displays detailed confirmation messages.
     * The window is centered, has a predefined size, and includes a single label
     * that shows the confirmation details passed as a parameter using HTML formatting for better readability.
     *
     * @param reservationID The reservation ID.
     * @param paymentConfirmation A string containing the payment confirmation message.
     */
    
    public editConfirmationWindow(String reservationID, String paymentConfirmation) {
        setTitle("Reservation Confirmation");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        String formattedMessage = "<html><div style='text-align: center; font-size: 16px; font-family: Arial;'>"
            + "<h1>Thank You!</h1>"
            + "<strong>Reservation ID:</strong> " + reservationID + "<br>"
            + "<strong>Payment Details:</strong><br>"
            + paymentConfirmation.replaceAll("\n", "<br>") 
            + "</div></html>";

        confirmationLabel = new JLabel(formattedMessage, SwingConstants.CENTER);
        getContentPane().add(confirmationLabel, BorderLayout.CENTER);
    
        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    
        setVisible(true);
    }
}