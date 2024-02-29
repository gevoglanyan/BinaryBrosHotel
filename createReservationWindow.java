// Need to Add Connection's Between GUI's
// Need to Add Database Functionality 

package CreateReservationWindows;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class createReservationWindow extends JFrame {

    private JTextField nameField, roomNumberField, checkInDateField, checkOutDateField;

    public createReservationWindow() {

        nameField = new JTextField(20);
        roomNumberField = new JTextField(5);
        checkInDateField = new JTextField(10);
        checkOutDateField = new JTextField(10);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(event -> reserveRoom());

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Room Number:"));
        add(roomNumberField);
        add(new JLabel("Check-in Date (YYYY-MM-DD):"));
        add(checkInDateField);
        add(new JLabel("Check-out Date (YYYY-MM-DD):"));
        add(checkOutDateField);
        add(new JLabel(""));
        add(reserveButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void reserveRoom() {

        String guestName = nameField.getText();
        String roomNumber = roomNumberField.getText();
        String checkInDate = checkInDateField.getText();
        String checkOutDate = checkOutDateField.getText();

        if (validateInput(guestName, roomNumber, checkInDate, checkOutDate)) {
            
            String confirmationMessage = "Reservation Confirmed:\n\n" + "Name: " + guestName + "\n" + "Room Number: " + roomNumber + "\n" 
                + "Check-in Date: " + checkInDate + "\n" + "Check-out Date: " + checkOutDate;

            JOptionPane.showMessageDialog(this, confirmationMessage, "Reservation Confirmation", JOptionPane.INFORMATION_MESSAGE); 
        } 
        
        else {
            JOptionPane.showMessageDialog(this, "Fill in all fields with a valid data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateInput(String name, String roomNumber, String checkInDate, String checkOutDate) {

        return !name.isEmpty() && !roomNumber.isEmpty() && isValidDate(checkInDate) && isValidDate(checkOutDate);
    }

    @SuppressWarnings("unused") //parsedDate
    private boolean isValidDate(String date) {

        try {

            SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
            dateTime.setLenient(false);
            Date parsedDate = dateTime.parse(date);

            return true;
        } 
        
        catch (Exception e) {
            return false;
        }
    }
}