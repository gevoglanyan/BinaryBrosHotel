import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
    Welcomes Users to Binary Bros Hotel
    Prompts Users with Login or Create Account Options
    @author Binary Bros
    @version 1.0
 */

public class welcomeWindow extends JFrame {
    public welcomeWindow() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Binary Bros Hotel Reservation System");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    
        JLabel welcomeLabel = createWelcomeLabel();
        JButton loginButton = createButton("Login", e -> onLoginClicked());
        JButton registerButton = createButton("Register", e -> onRegisterClicked());
    
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(welcomeLabel);
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
    
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
    
        mainPanel.add(titlePanel, gbc);
        mainPanel.add(buttonPanel, gbc);
    
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JLabel createWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Welcome to Binary Bros Hotel");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.BLUE);
        
        return welcomeLabel;
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        
        return button;
    }

    private void onLoginClicked() {
        userSelectionWindow loginWindow = new userSelectionWindow();
        loginWindow.setVisible(true);
    }

    private void onRegisterClicked() {
        createAccountWindow createAccountWindow = new createAccountWindow();
        createAccountWindow.setVisible(true);
    }
}