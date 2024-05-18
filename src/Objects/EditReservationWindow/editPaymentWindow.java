package Objects.EditReservationWindow;

import javax.swing.*;
import java.awt.*;

public class editPaymentWindow extends JFrame {
    private JComboBox<String> paymentMethodComboBox;
    private JTextField cardNumberField, expirationDateField, cvvField, zipCodeField;
    private JButton submitButton;

    // private String guestName;
    // private String roomNumber;
    // private String bedType;

    private String checkInDate;
    private String checkOutDate;

    public editPaymentWindow(String checkInDate, String checkOutDate) {
        // this.guestName = guestName;
        // this.roomNumber = roomNumber;
        // this.bedType = bedType;
        
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

        setTitle("Payment Processing");
        initializeComponents();
        setUpLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        paymentMethodComboBox = new JComboBox<>(new String[]{"Credit Card", "Debit Card"});
        cardNumberField = new JTextField(20);
        expirationDateField = new JTextField(10);
        cvvField = new JTextField(3);
        zipCodeField = new JTextField(5);

        submitButton = new JButton("Submit Payment");
        submitButton.addActionListener(e -> processPayment());
    }

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

    private void processPayment() {
        String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
        String cardNumber = cardNumberField.getText();
        String expirationDate = expirationDateField.getText();
        String cvv = cvvField.getText();
        String zipCode = zipCodeField.getText();

        if (isValidInput(paymentMethod, cardNumber, expirationDate, cvv, zipCode)) {
            new editConfirmationWindow(checkInDate, checkOutDate);
            // dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Fill in All Fields", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidInput(String paymentMethod, String cardNumber, String expirationDate, String cvv, String zipCode) {
        return paymentMethod != null && !cardNumber.isEmpty() && !expirationDate.isEmpty() && !cvv.isEmpty() && !zipCode.isEmpty();
    }

    class PaymentProcessor {

        public String processPayment(String paymentMethod, String cardNumber, String expirationDate, String cvv, String zipCode) {
            return String.format("Payment Processed Successfully for %s\nCard Number: %s\nExpiration Date: %s\nCVV: %s\nZip Code: %s",
                paymentMethod, cardNumber, expirationDate, cvv, zipCode);
        }
    }
}