package Mocking;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dineshkb on 26/12/14.
 */
public class Request {
    ArrayList<String> emailList;
    Date requestDate;

    public Request(ArrayList<String> userEmailList) {
        emailList = userEmailList;
    }

    public ArrayList<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(ArrayList<String> emailList) {
        this.emailList = emailList;
    }
}
