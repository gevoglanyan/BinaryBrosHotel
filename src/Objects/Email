import java.util.Properties;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import javax.activation.*;
import javax.mail.Transport;

/**
    A way to send emails to customer pertaining to their
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
    Properties properties = System.getProperties(); 
    properties.setProperty("mail.smtp.host", host); 
    Session session = Session.getDefaultInstance(properties); 
    /**
        Represents the message of the email
     */
    private String text;

 /**
        Sends email to the customer regarding their hotel reservation
        @param recipient the customer's email
        @param text the message in the email
     */
  public void sendMessage(String recipient, String text){
    this.recipient = recipient;
    this.text = text;
        try 
        { 
            MimeMessage message = new MimeMessage(session); 
    
            message.setFrom(new InternetAddress(sender)); 
    
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
    
            message.setSubject("Binary Bros Hotel Reservation"); 
    
            message.setText(text); 
    
            Transport.send(message); 
            System.out.println("Email has been sent"); 
        } 
        catch (MessagingException mex)  
        { 
        mex.printStackTrace(); 
        } 
    }
}
