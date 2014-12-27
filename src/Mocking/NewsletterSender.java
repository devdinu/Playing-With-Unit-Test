package Mocking;

import Models.Request;
import Models.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dineshkb on 27/12/14.
 */
public class NewsletterSender {

    private static final int MAX_LIMIT_PER_REQUEST = 5;

    public int send(Service service, ArrayList<String> users) {
        Response response = service.filter(new Request(users));
        List<String> subscribedUsers = response.getSubscribedUsers();
        System.out.println(Arrays.toString(subscribedUsers.toArray()));
        int result = -1;
        if (subscribedUsers.size() > MAX_LIMIT_PER_REQUEST) {
            System.out.println("Sending newsletter for " + MAX_LIMIT_PER_REQUEST + " subscribed users");
            Request newsletterSendRequest = new Request(subscribedUsers.subList(0, MAX_LIMIT_PER_REQUEST));
            response = service.sendNewsletter(newsletterSendRequest);
        }
        return response.totalSuccessFullRecipients();
    }
}
