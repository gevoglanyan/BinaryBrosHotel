package Objects;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import javax.mail.Session; 
import javax.mail.Transport;
import java.util.regex.Matcher; 
import java.util.regex.Pattern; 

/**
    Send emails to guests pertaining to their Binary Bros Hotel Reservation
    @author Binary Bros
    @version 1.0
 */

public class Email {
    
    /**
        Represents the guest's email address
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

    private String name;
    private String startDate;
    private String endDate;
    
    /**
        Constructs an email to send to guest
        @param guestEmail the guest's email address
     */
    public Email (String guestEmail) {
        setRecipient(guestEmail);
    }

    /**
        Creates default email template
       @param header Subject Line of the Email
       @param body Main Message of the Email
    */
    public void message(String header, String body) {
        if(!isValid(recipient)) break;
        
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
            Sends an email about guests recently made reservation
        */
        public void reservationMessage() {
            String message = "Your hotel reservation has been made." 
            + "Thank you " + name + " for choosing Binary Bros. for your hotel experience!"
            + "Your reservation is from " + startDate + " to " + endDate + "."
            + "To update or cancel your reservation, please edit your reservation.";
            String header = "Binary Bros: Hotel Reservation";
            message(header, message);
        }

        /**
            Sends an email about a reservation cancelation
        */
        public void cancelMessage() {
            String message = "Your hotel reservation with the Binary Bros has been cancelled.";
            String header = "Binary Bros: Reservation Cancelation";
            message(header, message);
        }

        /**
            Sends an email about an update to a reservation
        */
        public void updateMessage() {
            String message = "Your hotel reservation has been successfully updated." 
            + "Thank you again for choosing Binary Bros for your hotel experience!";
            String header = "Binary Bros: Reservation Update";
            message(header, message);
        }

        /**
            Sets the guest's email as the recipient address
            @param guestEmail the Guest Email Address
        */
        public void setRecipient(String guestEmail) {
            recipient = guestEmail;
        }

        /**
            Checks if the email address is valid
            @param email the email address
            @return validity of the email
         */
        public boolean isValid(String email){
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
            Pattern pat = Pattern.compile(emailRegex); 
            if (email == null) 
                return false; 
            return pat.matcher(email).matches(); 
        }
}
