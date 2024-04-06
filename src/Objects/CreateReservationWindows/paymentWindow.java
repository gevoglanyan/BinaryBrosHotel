import javax.swing.*;
import java.awt.*;

/**
    Prompts Users to Pay
    @author Binary Bros
    @version 1.0
 */

public class paymentWindow extends JFrame {
    private JComboBox <String> paymentMethodComboBox;
    private JTextField cardNumberField, expirationDateField, cvvField, zipCodeField;

    public paymentWindow() {
        paymentMethodComboBox = new JComboBox<>(new String[]{"Credit Card", "Debit Card"});
        cardNumberField = new JTextField(20);
        expirationDateField = new JTextField(10);
        cvvField = new JTextField(3);
        zipCodeField = new JTextField(5);
       
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> processPayment());

        setLayout(new GridLayout(5, 2));
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
        add(new JLabel(""));
        add(submitButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void processPayment() {
        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
        String cardNumber = cardNumberField.getText();
        String expirationDate = expirationDateField.getText();
        String cvv = cvvField.getText();
        String zipCode = zipCodeField.getText();

        if (isValidInput(paymentMethod, cardNumber, expirationDate, cvv, zipCode)) {
            paymentProcessor paymentProcessor = new paymentProcessor();
            String paymentConfirmation = paymentProcessor.processPayment(paymentMethod, cardNumber, expirationDate, cvv, zipCode);
            JOptionPane.showMessageDialog(this, paymentConfirmation, "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
        } 
        
        else {
            JOptionPane.showMessageDialog(this, "Fill in all fields with a valid data.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidInput(String paymentMethod, String cardNumber, String expirationDate, String cvv, String zipCode) {
        return paymentMethod != null && !cardNumber.isEmpty() && !expirationDate.isEmpty() && !cvv.isEmpty() && !zipCode.isEmpty();
    }

    public class paymentProcessor 
    {
        public String processPayment(String paymentMethod, String cardNumber, String expirationDate, String cvv, String zipCode) {
            return "Payment processed successfully:\n" + "Payment Method: " + paymentMethod + "\n" + "Card Number: " + cardNumber + "\n" 
                + "Expiration Date: " + expirationDate + "\n" + "CVV: " + cvv + "\n" + "Zip Code: " + zipCode;
        }
    }
}