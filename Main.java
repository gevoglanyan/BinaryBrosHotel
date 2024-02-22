public class Main{


    @SuppressWarnings({ "unused", "unchecked" })
    public static void main(String[] args) {

        PasswordsAndIDs IDSandPasses = new PasswordsAndIDs(); //
        LoginPage loginPage = new LoginPage(IDSandPasses.getLoginInfo());
        Reservation reservations = new Reservation();
        
    }
}