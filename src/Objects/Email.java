package Objects;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport;

/**
    A way to send emails to customers pertaining to their
    hotel reservation
    @author Binary Bros
    @version 1.0
 */

public class Email {
    /**
        Represents the customer's email address
     */
    private String recipient;
    /** 
        Represents the hotel's email address 
    */
    private String sender = "binarybros@gmail.com";
    /**
        Represents the host IP address
     */
    private String host = "127.0.0.1";

    /**
        Constructs an email to send to the guest
     */
    public Email (String guestEmail) {
        Properties properties = System.getProperties(); 
        properties.setProperty("mail.smtp.host", host); 
        Session session = Session.getDefaultInstance(properties); 
        setRecipient(guestEmail);
    }

    /**
        Creates default email template
       @param header subject line of the email
       @param body main message of the email
     */
    public void message(String header, String body) {
        String text = body;
        String subject = header;
        try 
        { 
            MimeMessage message = new MimeMessage(session); 
            message.setFrom(new InternetAddress(sender)); 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
            message.setSubject(subject); 
            message.setText(text); 
            Transport.send(message); 
            System.out.println("Email has been sent"); 
        } 
        catch (MessagingException mex)  
        { 
        mex.printStackTrace(); 
        } 
    }

        /**
            Sends email about guest's recently made hotel reservation
        */
        public void reservationMessage() {
            String message = "Your hotel reservation has been made. Thank you for choosing Binary Bros for your hotel experience! To update or cancel your reservation, check the reservation status on the website.";
            String header = "Binary Bros: Hotel Reservation";
            message(header, message);
        }

        /**
            Sends email about a reservation cancelation
        */
        public void cancelMessage() {
            String message = "Your hotel reservation with the Binary Bros has been cancelled.";
            String header = "Binary Bros: Reservation Cancelation";
            message(header, message);
        }

        /**
            Sends email about update to a reservation
        */

        public void updateMessage() {
            String message = "Your hotel reservation has been successfully updated! Thank you again for choosing Binary Bros for your hotel experience.";
            String header = "Binary Bros: Reservation Update";
            message(header, message);
        }

        /**
            Sets the guest's email as the recipient address
        */
        public void setRecipient(String guestEmail){
            recipient = guestEmail;
        }
}
