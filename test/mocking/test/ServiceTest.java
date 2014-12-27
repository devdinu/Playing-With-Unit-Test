package mocking.test;

import Mocking.Request;
import Mocking.Response;
import Mocking.Service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.powermock.api.easymock.PowerMock.createMock;

@RunWith(PowerMockRunner.class)
public class ServiceTest {

    private Service service;
    private Service serviceSpy;

    private static ArrayList<String> getUserEmailDump() {
        ArrayList<String> users = new ArrayList<String>();
        users.add("Jython");
        users.add("Cpython");
        users.add("python");
        users.add("Java");
        users.add("Scala");
        users.add("Python");
        users.add("Go");
        users.add("Django");
        users.add("ruby");
        users.add("rails");
        return users;
    }

    @Before
    public void setUp() {
        service = new Service();
        serviceSpy = spy(service);
        Response responseMock = generateMockedResponse();
    }

    @Test
    public void shouldFilterAllUsers() {
        Response responseMock = generateMockedResponse();
        when(serviceSpy.filter((Request) Matchers.any(Request.class))).thenReturn(responseMock);
        ArrayList<String> userEmailList = getUserEmailDump();
        Response response = serviceSpy.filter(new Request(userEmailList));
        assertNotNull("Response should not be null", response);
        List<String> subscribedUsers = response.getSubscribedUsers();
        List<String> unSubscribedUsers = response.getUnSubscribedUsers();
        assertEquals("Response should contain all users", userEmailList.size(), subscribedUsers.size() + unSubscribedUsers.size());
    }


    private Response generateMockedResponse() {
        Response responseMock = new Response();
        for (String user : getUserEmailDump())
            if (user.length() % 2 == 0)
                responseMock.addToUnSubscribedList(user);
            else
                responseMock.addToSubscribedList(user);
        return responseMock;
    }
}


