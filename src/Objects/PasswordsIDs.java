package Objects;

import java.util.HashMap;

public class PasswordsIDs{

    HashMap<String, String> loginInfo = new HashMap<String, String>();
    // Using hashmaps For Now Because They Store Key/Value Pairs, 
    // Perfect For Representing Username/Passwords For Now. SQL Will Be Implemented Later

    PasswordsIDs(String userName, String password){ // Constructor For "PasswordsAndIDs" Class
        loginInfo.put(userName, password);
    }

    PasswordsIDs(){
        loginInfo.put("Manager", "p@$$w0rd"); // Temp Login Info for Testing Purposes.
    }

    @SuppressWarnings("rawtypes")
    protected HashMap getLoginInfo(){
        return loginInfo;
    }
}