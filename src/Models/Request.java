package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dineshkb on 26/12/14.
 */
public class Request {
    List<String> emailList;
    Date requestDate;

    public Request(List<String> userEmailList) {
        emailList = userEmailList;
    }

    public List<String> getEmailList() {
        return emailList;
    }

    public void setEmailList(ArrayList<String> emailList) {
        this.emailList = emailList;
    }
}
