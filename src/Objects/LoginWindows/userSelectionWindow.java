import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class userSelectionWindow extends JFrame {
    public userSelectionWindow() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("User Selection");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton adminButton = createButton("Admin", e -> onAdminClicked());
        JButton guestButton = createButton("Guest", e -> onGuestClicked());
        
        buttonPanel.add(adminButton, gbc);
        buttonPanel.add(guestButton, gbc);
        
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
    }
    
    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        
        return button;
    }
    
    private void onAdminClicked() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] message = { "Username:", usernameField, "Password:", passwordField};

        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticate(username, password)) {
                JOptionPane.showMessageDialog(null, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                managerWindow loginWindow = new managerWindow();
                loginWindow.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
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