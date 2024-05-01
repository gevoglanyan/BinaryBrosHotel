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
     * Constructs a confirmationWindow which displays detailed confirmation messages.
     * The window is centered, has a predefined size, and includes a single label
     * that shows the confirmation details passed as a parameter using HTML formatting for better readability.
     *
     * @param guestName The guest's name.
     * @param roomNumber The room number reserved.
     * @param bedType The type of bed in the room.
     * @param checkInDate The check-in date.
     * @param checkOutDate The check-out date.
     * @param paymentConfirmation A string containing the payment confirmation message.
     */
    
    public confirmationWindow(String guestName, String roomNumber, String bedType, String checkInDate, String checkOutDate, String paymentConfirmation) {
        setTitle("Reservation Confirmation");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        String formattedMessage = "<html><div style='text-align: center; font-size: 16px; font-family: Arial;'>"
            + "<h1>Thank You!</h1>"
            + "<strong>Reservation Details:</strong><br>"
            + "Guest Name: " + guestName + "<br>"
            + "Room Number: " + roomNumber + "<br>"
            
            // Not Working Correctly
            // + "Bed Type: " + bedType + "<br>"

            + "Check-In Date: " + checkInDate + "<br>"
            + "Check-Out Date: " + checkOutDate + "<br><br>"
            + "<strong>Payment Details:</strong><br>"
            + paymentConfirmation.replaceAll("\n", "<br>") 
            + "</div></html>";

        confirmationLabel = new JLabel(formattedMessage, SwingConstants.CENTER);
        getContentPane().add(confirmationLabel, BorderLayout.CENTER);
    
        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(closeButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    
        setVisible(true);
    }
}