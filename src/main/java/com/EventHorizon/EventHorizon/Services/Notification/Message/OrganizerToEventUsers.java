package com.EventHorizon.EventHorizon.Services.Notification.Message;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;

import java.util.ArrayList;
import java.util.List;

public class OrganizerToEventUsers {

    public void createMessageAndSendToUsers(Event event, Organizer organizer, String organizerMessage) {
        List<User> goingUsers = getListOfGoingUsers(event);
        GmailMessage message = createMessage(event, organizer, organizerMessage);

    }

    private GmailMessage createMessage(Event event, Organizer organizer, String organizerMessage) {
        return new GmailMessage(
                "You are invited to the event " + event.getName() + " organized by " + organizer.getFirstName() + " " + organizer.getLastName() + ".",
                user -> "Hello " + user.getFirstName() + ",\n" +
                        "You are invited to the event " + event.getName() + " organized by " + organizer.getFirstName()
                        + " " + organizer.getLastName() + ".\n" +
                        organizerMessage
        );
    }

    private List<User> getListOfGoingUsers(Event event) {
        return new ArrayList<User>();
    }

}