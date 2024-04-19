import Objects.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
    Allows Users To Log Into Binary Bros Hotel System
    @author Binary Bros
    @version 1.0
 */

public class loginWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public loginWindow() {
        setTitle("Binary Bros Hotel Login");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Guest Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel loginPanel = new JPanel(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        gbc.insets = new Insets(10, 50, 10, 50); 

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(loginWindow.this, "Login Successful!");
                mainMenuWindow mainMenu = new mainMenuWindow();
                mainMenu.setVisible(true);
                loginWindow.this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(loginWindow.this, "Login Failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            passwordField.setText("");
        });

        loginPanel.add(titleLabel, gbc); 
        loginPanel.add(usernameLabel, gbc);
        loginPanel.add(usernameField, gbc);
        loginPanel.add(passwordLabel, gbc);
        loginPanel.add(passwordField, gbc);
        loginPanel.add(loginButton, gbc);
        
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());

        add(loginPanel, BorderLayout.CENTER); 

        setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    
        try {
            connection = Database.getConnection();
    
            String sql = "SELECT password FROM Accounts WHERE username = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
    
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);

            } finally {
                try {
                    if (rs != null) 
                        rs.close();
                    if (pstmt != null) 
                        pstmt.close();
                    if (connection != null) 
                        connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error closing database resources: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
        }

        return false;
    }
    
    /* 

    // Can Become Admin Login (Possibly)

    private boolean authenticate(String username, String password) {
        boolean isAdmin = username.equals("admin") && password.equals("password");
        boolean isTempUser = username.equals("temp") && password.equals("temp");

        return isAdmin || isTempUser;
    }

    */
}