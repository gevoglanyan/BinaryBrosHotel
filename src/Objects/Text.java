/**
    A way to send text messages to customers regarding their 
    hotel reservation
    @author Binary Bros
    @version 1.0
 */
public class Text {
    /**
        Represents the guest's phone number
     */
    private String recipient;
//Needs more work
    public Text (String number){
        setNumber(number);

    }

    /**
        Sets the phone number of the recipient
        @param number the guest's phone number
     */
    public void setNumber(String number){
        recipient = number;
    }
    /**
        Sends text for the initial hotel reservation
     */
    public void reserveText(){
        System.out.println("Thank you for choosing Binary Bros for your hotel experience! Your hotel has been successfully reserved.");

    }

    /**
        Sends text for a reservation cancelation
     */
    public void cancelText(){
        System.out.println("Your hotel reservation has been successfully canceled");
    }

    /**
        Sends text for reservation update
     */
    public void updateText(){
        System.out.println("Your hotel reservation has been successfully updated!");
    }
}
