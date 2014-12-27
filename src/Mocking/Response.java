package Mocking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dineshkb on 26/12/14.
 */
public class Response {
    List<String> subscribedUsers;
    List<String> unSubscribedUsers;
    Date processedDate;

    public Response() {
        subscribedUsers = new ArrayList<>();
        unSubscribedUsers = new ArrayList<>();
//      Set ProcessDate and Other Fields Time
    }

    public List<String> getUnSubscribedUsers() {
        return unSubscribedUsers;
    }

    public List<String> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void addToSubscribedList(String user) {
        subscribedUsers.add(user);
    }

    public void addToUnSubscribedList(String user) {
        unSubscribedUsers.add(user);
    }

    public void setSubscribedUsers(List<String> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }

    public void setUnSubscribedUsers(List<String> unSubscribedUsers) {
        this.unSubscribedUsers = unSubscribedUsers;
    }
}
