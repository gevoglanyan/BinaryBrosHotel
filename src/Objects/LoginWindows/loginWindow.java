import javax.swing.*;
import java.awt.*;

public class loginWindow extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public loginWindow() {
        setTitle("Binary Bros Hotel Login");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Binary Bros Hotel Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));

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
                loginWindow.this.dispose(); // Close the login window
            } else {
                JOptionPane.showMessageDialog(loginWindow.this, "Login Failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            passwordField.setText("");
        });

        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        setLayout(new BorderLayout());

        add(titleLabel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        boolean isAdmin = username.equals("admin") && password.equals("password");
        boolean isTempUser = username.equals("temp") && password.equals("temp");

        return isAdmin || isTempUser;
    }
}
