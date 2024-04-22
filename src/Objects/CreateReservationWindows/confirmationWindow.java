/* 
    Need to Add Better Confirmation Message

    - Name
    - Room Number
    - Bed Type
    - Check In Date
    - Check Out Date
    - Payment Success (INFO)
*/

import javax.swing.*;

/**
 * This class provides a window that displays a payment confirmation message to the user.
 * The window is intended to confirm reservation details such as name, room number, bed type,
 * check-in and check-out dates, and the status of the payment.
 * 
 * @author Binary Bros
 * @version 1.0
 */

 public class confirmationWindow extends JFrame {

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
    
    public confirmationWindow(String paymentConfirmation) {
        setTitle("Confirmation");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel(paymentConfirmation, SwingConstants.CENTER));
        setVisible(true);
    }
}