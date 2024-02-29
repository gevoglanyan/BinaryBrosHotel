import javax.swing.JButton; //need to refine + add in phone number option + connect with reservation class
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class confirmationWindow {

    public static void main(final String[] args) {
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        button.setText("Your reservation is confirmed!");
        parent.add(button);
        parent.pack();
        parent.setVisible(true);
        button.addActionListener (new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = JOptionPane.showInputDialog(parent, 
                 "Send confirmation to your email?", null);
            }
        }
);
    }

}
