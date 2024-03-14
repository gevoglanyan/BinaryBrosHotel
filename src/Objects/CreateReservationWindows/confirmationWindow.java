import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class confirmationWindow extends JFrame {
    private JLabel nameLabel, dateLabel, roomTypeLabel;
    private JTextField nameField, dateField;
    private JComboBox<String> roomTypeComboBox;
    private JButton confirmButton;

    public confirmationWindow() {
        setTitle("Binary Bros Hotel Reservation Confirmation");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        dateLabel = new JLabel("Date:");
        dateField = new JTextField();

        roomTypeLabel = new JLabel("Room Type:");
        String[] roomTypes = {"Single", "Double", "Suite"};
        roomTypeComboBox = new JComboBox<>(roomTypes);

        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String date = dateField.getText();
                String roomType = (String) roomTypeComboBox.getSelectedItem();

                String confirmationMessage = "Reservation Summary:\n" +
                        "Name: " + name + "\n" +
                        "Date: " + date + "\n" +
                        "Room Type: " + roomType;

                JOptionPane.showMessageDialog(null, confirmationMessage, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        add(nameLabel);
        add(nameField);
        add(dateLabel);
        add(dateField);
        add(roomTypeLabel);
        add(roomTypeComboBox);
        add(new JLabel());
        add(confirmButton);

        setVisible(true);
    }
}