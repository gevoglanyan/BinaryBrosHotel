import Objects.Database;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Provides an interface for new users to create an account for the Binary Bros Hotel system.
 * This class extends JFrame and includes form inputs for user details such as full name, username,
 * email, password, date of birth, and address. It also handles the creation and validation of user
 * accounts, storing them in a database upon successful validation.
 * 
 * @author Binary Bros (Harutyun Gevoglanyan)
 * @date 02/28/2024
 * @version 1.0
 */

public class createAccountWindow extends JFrame {
    private JTextField fullNameField, usernameField, emailField, dobField, addressField;
    private JPasswordField passwordField;

    /**
     * Constructor for createAccountWindow that initializes the user interface and makes the window visible.
     */

    public createAccountWindow() {
        super("Account Creation");
        initializeUI();
        pack();
        setSize(500, 400); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

     /**
     * Initializes the user interface components of the account creation form.
     * Sets up a form with labeled text fields for user input and a button to submit the form.
     */

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

    /**
     * Returns the appropriate input field based on the provided index.
     * 
     * @param index The index corresponding to the input field.
     * @return JComponent representing the input field.
     */

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

    /**
     * Attempts to create a new user account by inserting the input data into the database.
     * Validates the input data, particularly the date format, and handles SQL and parsing exceptions.
     */
    
    private void createAccount() {
        String fullName = fullNameField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String dob = dobField.getText();
        String address = addressField.getText();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
            Date dobDate = inputDateFormat.parse(dob);
            String formattedDob = outputDateFormat.format(dobDate); 

            connection = Database.getConnection();
    
            String sql = "INSERT INTO Accounts (fullName, username, email, password, dateOfBirth, address) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, fullName);
            statement.setString(2, username);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, formattedDob);
            statement.setString(6, address);
    
            statement.executeUpdate();
    
            JOptionPane.showMessageDialog(this, "Account Created Successfully.");
            clearFields();
            dispose();
            
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid Date of Birth Format. Use MM/dd/yyyy.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "ERROR" + e.getMessage());
        } finally {
            try {
                if (statement != null) 
                    statement.close();
                if (connection != null) 
                    connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "ERROR" + e.getMessage());
            }
        }
    }

    /**
     * Clears all input fields after an account creation attempt or upon successful account creation.
     */
    
    private void clearFields() {
        fullNameField.setText("");
        usernameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        dobField.setText("");
        addressField.setText("");
    }
}