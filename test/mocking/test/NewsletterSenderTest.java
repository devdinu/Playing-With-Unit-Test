package mocking.test;

import Mocking.NewsletterSender;
import Mocking.Service;
import Models.Request;
import Models.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.spy;
    import static org.mockito.Mockito.when;

public class NewsletterSenderTest {


    private static final int MAX_LIMIT_PER_REQUEST = 5;
    private Utility utility;
    private Service service;
    private Service serviceSpy;
    private NewsletterSender sender;

    @Before
    public void setUp() {
        utility = new Utility();
        service = new Service();
        serviceSpy = spy(service);
        sender = new NewsletterSender();
    }

    @Test
    public void WrongTestForValidation_shouldOnlySendMaximumNewsletter() {
        when(serviceSpy.filter((Request) Matchers.any(Request.class))).thenReturn(utility.generateMockedResponse(Utility.getUserEmailDump()));

        Response response = utility.generateMockedResponse(Utility.getUserEmailDump());
        response.setTotalSuccessFullRecipients(response.getSubscribedUsers().size());
        when(serviceSpy.sendNewsletter((Request) Matchers.any(Request.class))).thenReturn(response);

        int totalNewslettersSent = sender.send(serviceSpy, utility.getUserEmailDump());
        assertFalse("Request should not exceed maximum limit", totalNewslettersSent > MAX_LIMIT_PER_REQUEST);
    }

    @Test
    public void RightWayWithDynamicMock_TestingMaximumRecipientsInSendingNewsletter() {
        when(serviceSpy.filter((Request) Matchers.any(Request.class))).thenReturn(utility.generateMockedResponse(Utility.getUserEmailDump()));

        when(serviceSpy.sendNewsletter((Request) Matchers.any(Request.class))).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Request request = (Request) invocation.getArguments()[0];
                //whatever condition that makes sending fail
                Iterator<String> subscriberList = request.getEmailList().iterator();
                int sentCount = 0;
                while (subscriberList.hasNext()) {
                    String user = subscriberList.next();
                    if (!user.matches(".*\\d+.*")) {
                        sentCount++;
                    } else {
                        System.out.println("super this is removed" + user);
                    }
                }
                Response response = new Response();
                response.setTotalSuccessFullRecipients(sentCount);
                return response;
            }
        });
        int totalNewslettersSent = sender.send(serviceSpy, utility.getUserEmailDump());
        System.out.println(totalNewslettersSent + "sent");
        assertFalse("Request should not exceed maximum limit", totalNewslettersSent > MAX_LIMIT_PER_REQUEST);
    }
}