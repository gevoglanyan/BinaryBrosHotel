import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Represents the welcome window for the Binary Bros Hotel application, providing user interface components 
 * that allow users to either log in or create a new account.
 * This class extends JFrame and sets up a GUI with welcome message, login, and register options.
 * 
 * The GUI layout includes a main panel with a welcome label and buttons for logging in and registering. 
 * Each button, when clicked, triggers a respective action for user authentication or account creation.
 * 
 * @author Binary Bros
 * @version 1.0
 */

public class welcomeWindow extends JFrame {
    public welcomeWindow() {
        initializeUI();
    }

    /**
     * Initializes and sets up the user interface components of the window.
     * Configures the frame size, behavior on close, and layout, along with creating and adding necessary GUI components.
     */

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

    /**
     * Creates and returns a JLabel that displays the welcome message.
     * 
     * @return JLabel with a predefined message, font, and color.
     */
    
    private JLabel createWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Welcome to Binary Bros Hotel");
        
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.BLUE);
        
        return welcomeLabel;
    }

    /**
     * Creates a button with an action listener.
     * 
     * @param text The text to display on the button.
     * @param actionListener The action listener to attach to the button.
     * @return JButton configured with the specified text and listener.
     */

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        
        button.addActionListener(actionListener);
        
        return button;
    }

    /**
     * Defines the action to be taken when the login button is clicked.
     * Opens a new window where the user can log into their account.
     */

    private void onLoginClicked() {
        userSelectionWindow loginWindow = new userSelectionWindow();
        
        loginWindow.setVisible(true);
    }

    /**
     * Defines the action to be taken when the register button is clicked.
     * Opens a new window where the user can create a new account.
     */

    private void onRegisterClicked() {
        createAccountWindow createAccountWindow = new createAccountWindow();
        
        createAccountWindow.setVisible(true);
    }
}