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
 * Send emails to guests pertaining to their Binary Bros Hotel Reservation
 *  
 * @author Binary Bros (Diego Arteaga)
 * @date 04/03/2024
 * @version 1.0
 */

@SuppressWarnings("unused")
public class Email {
    
    /**
     * Represents the guest's email address
     */

    private String recipient;

    /** 
     * Represents the hotel's email address 
     */

    private String sender = "binarybroshotel@gmail.com";

    /**
     * Represents the host IP address
     */

    private String host = "127.0.0.1";

    /**
     * Represents the recipient's name
     */

    private String name;

    /**
     * Represents the start date of the reservation
     */

    private String startDate;

    /**
     * Represents the end date of the reservation
     */

    private String endDate;
    
    /**
     * Constructs an email to send to guest
     * @param guestEmail the guest's email address
     * @param name the guest's name
     * @param checkIn the check-in date
     * @param checkOut the check-out date
     */

    public Email (String guestEmail, String name, String checkIn, String checkOut) {
        setRecipient(guestEmail);
        setName(name);
        setCheckIn(checkIn);
        setCheckOut(checkOut);
    }

    /**
     * Creates default email template
     * @param header subject line of the email
     * @param body main message of the email
     */

    public void message(String header, String body) {
        if (!isValid(recipient)) 
            return;
        
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
     * Sends an email about guests recently made reservation
     */

    public void reservationMessage() {
        String message = name + ". Your reservation is confirmed for " + startDate + " to " 
        + endDate + ".";
        String header = "Reservation Confirmation";
        message(header, message);
    }

    /**
     * Sends an email about a reservation cancelation
     */

    public void cancelMessage() {
        String message = "Your reservation is cancelled for " + startDate + " to " 
        + endDate + ".";
        String header = "Reservation Cancellation";
        message(header, message);
    }

    /**
     * Sends an email about an update to a reservation
     */

    public void updateMessage() {
        String message = "Your reservation is updated for " + startDate + " to " 
        + endDate + ".";
        String header = "Reservation Update";
        message(header, message);
    }

    /**
     * Sets the guest's email as the recipient address
     * @param guestEmail the guest email address
     */
    
    public void setRecipient(String guestEmail) {
        recipient = guestEmail;
    }

    /**
     * Sets the guest's name as the name
     * @param name the guest's name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the proper check-in date
     * @param checkIn the check-in date
     */
    
    public void setCheckIn(String checkIn){
        startDate = checkIn;
    }

    /**
     * Sets the proper check-out date
     * @param checkOut the check-out date
     */

    public void setCheckOut(String checkOut){
        endDate = checkOut;
    }

    /**
     * Checks if the email address is valid
     * @param email the email address
     * @return validity of the email
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