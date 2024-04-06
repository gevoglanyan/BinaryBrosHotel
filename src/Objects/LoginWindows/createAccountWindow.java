//package Objects.LoginWindows;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
    Allows New Users To Create an Account For Binary Bros Hotel
    @author Binary Bros
    @version 1.0
 */

public class createAccountWindow extends JFrame {
    private JTextField fullNameField, usernameField, emailField, dobField, addressField;
    private JPasswordField passwordField;

    public createAccountWindow() {
        super("Account Creation");
        initializeUI();
        pack();
        setSize(500, 400); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeUI() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        fullNameField = new JTextField(15);
        usernameField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        dobField = new JTextField(15);
        addressField = new JTextField(15);

        String[] labelStrings = {
            "Full Name:", "Username:", "Email Address:", "Password:", 
            "Date of Birth (MM/DD/YYYY):", "Home Address:"
        };

        for (int i = 0, y = 0; i < labelStrings.length; i++, y++) {
            gbc.gridx = 0; 
            gbc.gridy = y; 
            gbc.anchor = GridBagConstraints.EAST;
            formPanel.add(new JLabel(labelStrings[i]), gbc);

            gbc.gridx = 1; 
            gbc.gridy = y; 
            gbc.fill = GridBagConstraints.HORIZONTAL;
            formPanel.add(getInputField(i), gbc);
        }

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> createAccount());
        gbc.gridx = 0; 
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(createAccountButton, gbc);

        add(formPanel);
    }

    private JComponent getInputField(int index) {
        switch (index) {
            case 0: return fullNameField;
            case 1: return usernameField;
            case 2: return emailField;
            case 3: return passwordField;
            case 4: return dobField;
            case 5: return addressField;
            default: return new JTextField(15);
        }
    }

    private void createAccount() {
        String fullName = fullNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String dob = dobField.getText();
        String address = addressField.getText();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/DD/YYYY");
            Date dobDate = dateFormat.parse(dob);
            String formattedDob = dateFormat.format(dobDate);

            String accountInfo = String.format("Account Created\nFull Name: %s\nUsername: %s\nEmail: %s\nPassword: %s\nDate of Birth: %s\nAddress: %s",
                                                fullName, username, email, password, formattedDob, address);
            JOptionPane.showMessageDialog(this, accountInfo);
            clearFields();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth format. Please use MM/DD/YYYY.");
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