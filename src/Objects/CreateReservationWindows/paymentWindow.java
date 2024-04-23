import javax.swing.*;
import java.awt.*;

/**
 * Provides a user interface for processing payments within the Binary Bros Hotel System.
 * This window allows users to enter their payment details including payment method,
 * card number, expiration date, CVV, and zip code, and processes these details.
 *
 * @author Binary Bros
 * @version 1.0
 */

public class paymentWindow extends JFrame {
    private JComboBox<String> paymentMethodComboBox;
    private JTextField cardNumberField, expirationDateField, cvvField, zipCodeField;
    private JButton submitButton; 

     /**
      * Constructs the payment window and initializes its components and layout.
      */
    public paymentWindow() {
        setTitle("Payment Processing");
        initializeComponents();
        setUpLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes the components used in the payment form.
     */
    private void initializeComponents() {
        paymentMethodComboBox = new JComboBox<>(new String[]{"Credit Card", "Debit Card"});
        cardNumberField = new JTextField(20);
        expirationDateField = new JTextField(10);
        cvvField = new JTextField(3);
        zipCodeField = new JTextField(5);

        submitButton = new JButton("Submit Payment"); 
        submitButton.addActionListener(e -> processPayment());
    }

    /**
     * Sets up the layout of the payment window using a grid layout to organize
     * labels and fields in a form-like manner.
     */
    private void setUpLayout() {
        setLayout(new GridLayout(6, 2, 5, 5));
        add(new JLabel("Payment Method:"));
        add(paymentMethodComboBox);

        add(new JLabel("Card Number:"));
        add(cardNumberField);

        add(new JLabel("Expiration Date (MM/YY):"));
        add(expirationDateField);

        add(new JLabel("CVV:"));
        add(cvvField);

        add(new JLabel("Zip Code:"));
        add(zipCodeField);

        add(new JLabel());
        add(submitButton); 
    }

    /**
     * Processes the payment information entered by the user.
     * Validates input before processing and displays a confirmation or error message.
     */
    private void processPayment() {
        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
        String cardNumber = cardNumberField.getText();
        String expirationDate = expirationDateField.getText();
        String cvv = cvvField.getText();
        String zipCode = zipCodeField.getText();

        if (isValidInput(paymentMethod, cardNumber, expirationDate, cvv, zipCode)) {
            PaymentProcessor paymentProcessor = new PaymentProcessor();
            String paymentConfirmation = paymentProcessor.processPayment(paymentMethod, cardNumber, expirationDate, cvv, zipCode);

            JOptionPane.showMessageDialog(this, paymentConfirmation, "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
            dispose();

            new confirmationWindow(paymentConfirmation); 
        } else {
            JOptionPane.showMessageDialog(this, "Fill in All Fields", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

     /**
     * Validates that all payment input fields are filled in correctly.
     *
     * @param paymentMethod The selected payment method.
     * @param cardNumber The card number input.
     * @param expirationDate The card expiration date.
     * @param cvv The card CVV number.
     * @param zipCode The zip code associated with the card.
     * @return true if all fields are valid, false otherwise.
     */
    private boolean isValidInput(String paymentMethod, String cardNumber, String expirationDate, String cvv, String zipCode) {
        return paymentMethod != null && !cardNumber.isEmpty() && !expirationDate.isEmpty() && !cvv.isEmpty() && !zipCode.isEmpty();
    }
    
    /**
     * Inner class that handles the processing of payment details.
     */
    class PaymentProcessor {

        /**
         * Simulates payment processing.
         *
         * @param paymentMethod The payment method used.
         * @param cardNumber The card number.
         * @param expirationDate The expiration date of the card.
         * @param cvv The CVV of the card.
         * @param zipCode The zip code linked to the card.
         * @return A string summarizing the payment information, confirming processing.
         */
        public String processPayment(String paymentMethod, String cardNumber, String expirationDate, String cvv, String zipCode) {
            return String.format("Payment Processed Successfully for %s:\nCard Number: %s\nExpiration Date: %s\nCVV: %s\nZip Code: %s",
                    paymentMethod, cardNumber, expirationDate, cvv, zipCode);
        }
    }
}
