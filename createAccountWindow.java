// Need to Add Connection Between GUI's

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class createAccountWindow extends JFrame {
    
    private JTextField fullNameField, usernameField, emailField, dobField, addressField;
    private JPasswordField passwordField;

    public createAccountWindow() {

        JLabel[] labels = 
        {
                new JLabel("Full Name:"), new JLabel("Username:"),
                new JLabel("Email Address:"), new JLabel("Password:"),
                new JLabel("Date of Birth (MM/DD/YYYY):"), new JLabel("Home Address:")
        };

        fullNameField = new JTextField(20);
        usernameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        dobField = new JTextField(10);
        addressField = new JTextField(20);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> createAccount());

        setLayout(new GridLayout(labels.length + 1, 2, 10, 10)); 

        for (int i = 0; i < labels.length; i++) {
            add(labels[i]);
            add(i == 3 ? passwordField : i == 4 ? dobField : new JTextField(20));
        }

        add(new JLabel());
        add(createAccountButton);

        setTitle("Account Creation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createAccount() {

        String fullName = fullNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        String dobText = dobField.getText();
        String address = addressField.getText();

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date dob = dateFormat.parse(dobText);
            String formattedDob = dateFormat.format(dob);

            String accountInfo = String.format(
                    "Account created\n Full Name: %s\nUsername: %s\nEmail: %s\nPassword: %s\nDate of Birth: %s\nAddress: %s",
                    fullName, username, email, password, formattedDob, address
            );

            JOptionPane.showMessageDialog(this, accountInfo);

            clearFields();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth format. Please use MM/dd/yyyy.");
        }
    }

    private void clearFields() {
        fullNameField.setText("");
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        dobField.setText("");
        addressField.setText("");
    }
}