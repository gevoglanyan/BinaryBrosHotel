import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
    Screen Guest's and Hotel Employee See Before Login
    @author Binary Bros
    @version 1.0
 */

public class userSelectionWindow extends JFrame {
    public userSelectionWindow() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("User Selection");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        getContentPane().add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 20, 20);  

        JButton adminButton = createButton("Admin", null);
        JButton guestButton = createButton("Guest", null);
        
        adminButton.setPreferredSize(new Dimension(200, 50)); 
        guestButton.setPreferredSize(new Dimension(200, 50)); 
        
        adminButton.addActionListener(e -> onAdminClicked());
        guestButton.addActionListener(e -> onGuestClicked());
        
        mainPanel.add(adminButton, gbc);
        mainPanel.add(guestButton, gbc);
    }
    
    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        
        return button;
    }
    
    private void onAdminClicked() {
        JFrame loginFrame = new JFrame("Binary Bros Hotel Admin Login");

        loginFrame.setSize(800, 800);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
    
        JLabel titleLabel = new JLabel("Admin Login");

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
    
        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
    
        JButton loginButton = new JButton("Login");
        
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
    
            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(loginFrame, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
    
                managerWindow loginWindow = new managerWindow();
                loginWindow.setVisible(true);
                loginFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Incorrect Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        loginPanel.add(titleLabel, gbc);
        loginPanel.add(usernameLabel, gbc);
        loginPanel.add(usernameField, gbc);
        loginPanel.add(passwordLabel, gbc);
        loginPanel.add(passwordField, gbc);
        loginPanel.add(loginButton, gbc);
    
        loginFrame.setLayout(new BorderLayout());
        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginFrame.setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        boolean isAdmin = username.equals("admin") && password.equals("password");
        boolean isTempUser = username.equals("temp") && password.equals("temp");
        
        return isAdmin || isTempUser;
    }

    private void onGuestClicked() {
        loginWindow loginWindow = new loginWindow();
        loginWindow.setVisible(true);
    }
}