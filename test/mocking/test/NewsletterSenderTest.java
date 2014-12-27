package mocking.test;

import Mocking.NewsletterSender;
import Mocking.Service;
import Models.Request;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.*;
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
        when(serviceSpy.filter((Request) Matchers.any(Request.class))).thenReturn(utility.generateMockedResponse());
        when(serviceSpy.sendNewsletter((Request) Matchers.any(Request.class))).thenReturn(utility.generateMockedResponse());
    }

    @Test
    public void shouldOnlySendMaximum5Newsletter() {
        int totalNewslettersSent = sender.send(serviceSpy, utility.getUserEmailDump());
        assertNotEquals("The current list is not Empty", 0, totalNewslettersSent);
        assertTrue("Request should not exceed maximum limit", totalNewslettersSent < MAX_LIMIT_PER_REQUEST);
    }
}