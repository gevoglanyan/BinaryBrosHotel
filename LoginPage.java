import java.awt.*;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;


public class LoginPage implements ActionListener{

    JFrame frame = new JFrame();

    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JButton createAccountButton = new JButton("Create an Account");

    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();

    JLabel userIDLabel = new JLabel("User ID: ");
    JLabel userPasswordLabel = new JLabel("Password: ");
    JLabel messageLabel = new JLabel("Enter Information");

    JLabel hotelLabel = new JLabel("Binary Bros Hotel Services");

    HashMap<String,String> loginInfo = new HashMap<String,String>(); //globally available to login page now

    LoginPage (HashMap<String, String> LoginInfoOriginal){

        //code for login page window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);

        loginInfo = LoginInfoOriginal;

        //user ID
        userIDLabel.setBounds(50, 100, 75, 25);
        frame.add(userIDLabel);
        userIDField.setBounds(125, 100, 200, 25);
        frame.add(userIDField);


        //user password
        userPasswordField.setBounds(125, 150, 200, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);
        frame.add(userPasswordField);
        frame.add(userPasswordLabel);
        userPasswordField.setEchoChar('*');

        //message label
        messageLabel.setBounds(125, 300, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        frame.add(messageLabel);

        //login button
        loginButton.setBounds(125, 200, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        frame.add(loginButton);

        //reset button
        resetButton.setBounds(225, 200, 100, 25);
        resetButton.addActionListener(this);
        resetButton.setFocusable(false);
        frame.add(resetButton);

        //create account button
        createAccountButton.setBounds(125,230,200,25);
        createAccountButton.addActionListener(this);
        createAccountButton.setFocusable(false);
        frame.add(createAccountButton);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) { //USER CLICKS RESET BUTTON
            userIDField.setText(""); //clears text field
            userPasswordField.setText("");//clears text field
            messageLabel.setForeground(Color.BLACK);
            messageLabel.setText("Enter Information");
        }
        if (e.getSource() == loginButton) {//USER CLICKS LOGIN BUTTON
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if(loginInfo.containsKey(userID)){
                if(loginInfo.get(userID).equals(password)) {
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login successful!");
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Incorrect Password, try again.");
                }
            } else {
                messageLabel.setForeground(Color.orange);
                messageLabel.setText("Account not found...");
            }

        }
        if (e.getSource() == createAccountButton) { //USER CLICKS CREATE AN ACCOUNT BUTTON

        }
        

    }

}