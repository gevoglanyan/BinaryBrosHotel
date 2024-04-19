package Objects;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport;

/**
    Send Emails to Guests Pertaining to Their Hotel Reservation
    @author Binary Bros
    @version 1.0
 */

public class Email {
    /**
        Represents the Guests Email Address
     */
    private String recipient;
    /** 
        Represents the Hotel's Email Address 
    */
    private String sender = "binarybros@gmail.com";
    /**
        Represents the Host IP Address
     */
    private String host = "127.0.0.1";
    /**
        Constructs an Amail to Send to Guest
     */
    public Email (String guestEmail) {
        setRecipient(guestEmail);
    }

    /**
        Creates Default Email Template
       @param header Subject Line of the Email
       @param body Main Message of the Email
    */

    public void message(String header, String body) {
        String text = body;
        String subject = header;
        
        Properties properties = System.getProperties(); 
        properties.setProperty("mail.smtp.host", host); 
        
        Session session = Session.getDefaultInstance(properties); 
        try 
        { 
            MimeMessage message = new MimeMessage(session); 
            message.setFrom(new InternetAddress(sender)); 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
            message.setSubject(subject); 
            message.setText(text); 
            Transport.send(message); 
            
            System.out.println("Email Sent!"); 
        } 
        catch (MessagingException mex) { 
            mex.printStackTrace(); 
        } 
    }

        /**
            Sends an Email About Guests Recently Made Reservation
        */
        public void reservationMessage() {
            String message = "Your hotel reservation has been made. Thank you for choosing Binary Bros for your hotel experience! To update or cancel your reservation, check the reservation status on the website.";
            String header = "Binary Bros: Hotel Reservation";
            message(header, message);
        }

        /**
            Sends an Email About a Reservation Cancelation
        */
        public void cancelMessage() {
            String message = "Your hotel reservation with the Binary Bros has been cancelled.";
            String header = "Binary Bros: Reservation Cancelation";
            message(header, message);
        }

        /**
            Sends an Email About an Update to a Reservation
        */

        public void updateMessage() {
            String message = "Your hotel reservation has been successfully updated! Thank you again for choosing Binary Bros for your hotel experience.";
            String header = "Binary Bros: Reservation Update";
            message(header, message);
        }

        /**
            Sets the Guests Email as the Recipient Address
        */
        public void setRecipient(String guestEmail) {
            recipient = guestEmail;
        }
}