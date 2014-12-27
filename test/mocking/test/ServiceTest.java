package mocking.test;

import Models.Request;
import Models.Response;
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

    private Utility utility;
    private Service service;
    private Service serviceSpy;

    @Before
    public void setUp() {
        utility = new Utility();
        service = new Service();
        serviceSpy = spy(service);
    }

    @Test
    public void shouldFilterAllUsers() {
        Response responseMock = utility.generateMockedResponse(Utility.getUserEmailDump());
        when(serviceSpy.filter((Request) Matchers.any(Request.class))).thenReturn(responseMock);
        ArrayList<String> userEmailList = Utility.getUserEmailDump();
        Response response = serviceSpy.filter(new Request(userEmailList));
        assertNotNull("Response should not be null", response);
        List<String> subscribedUsers = response.getSubscribedUsers();
        List<String> unSubscribedUsers = response.getUnSubscribedUsers();
        assertEquals("Response should contain all users", userEmailList.size(), subscribedUsers.size() + unSubscribedUsers.size());
    }

}


