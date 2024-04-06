import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
    Allows Users to Create a Reservation
    @author Binary Bros
    @version 1.0
 */

public class createReservationWindow extends JFrame {
    private JTextField nameField, checkInDateField, checkOutDateField;
    private JComboBox<String> roomOptions;

    public createReservationWindow() {
        setTitle("Make a Reservation");
        nameField = new JTextField(20);

        String[] rooms = {"101", "102"};
        roomOptions = new JComboBox<>(rooms);

        checkInDateField = new JTextField(10);
        checkOutDateField = new JTextField(10);

        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(event -> reserveRoom());

        setLayout(new GridLayout(5, 2));
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Room Selection:"));
        add(roomOptions);
        add(new JLabel("Check-in Date (YYYY-MM-DD):"));
        add(checkInDateField);
        add(new JLabel("Check-out Date (YYYY-MM-DD):"));
        add(checkOutDateField);
        add(new JLabel(""));
        add(reserveButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
    }

    private void reserveRoom() {
        String guestName = nameField.getText();
        String roomNumber = (String) roomOptions.getSelectedItem();
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

    
    private boolean isValidDate(String date) {
        try {
            SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
            dateTime.setLenient(false);
            @SuppressWarnings("unused")
            Date parsedDate = dateTime.parse(date);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}