package Mocking;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dineshkb on 27/12/14.
 */
public class NewsletterSender {

    public int send(Service service, ArrayList<String> users) {
        Response response = service.filter(new Request(users));
        List<String> subscribedUsers = response.getSubscribedUsers();
        System.out.println("Sending newsletter for "+ subscribedUsers.size() + " subscribed users");
        return service.sendNewsletter(subscribedUsers);
    }
}
