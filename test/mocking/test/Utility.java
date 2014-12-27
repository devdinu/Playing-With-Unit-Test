package mocking.test;

import Models.Response;

import java.util.ArrayList;

/**
 * Created by dineshkb on 27/12/14.
 */
public class Utility {
    static ArrayList<String> getUserEmailDump() {
        ArrayList<String> users = new ArrayList<String>();
        users.add("User11");
        users.add("Jython");
        users.add("User22");
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

    Response generateMockedResponse(ArrayList<String> userEmailList) {
        Response responseMock = new Response();
        for (String user : userEmailList)
            if (user.length() % 2 != 0)
                responseMock.addToUnSubscribedList(user);
            else
                responseMock.addToSubscribedList(user);
        return responseMock;
    }
}
