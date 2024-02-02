package com.EventHorizon.EventHorizon.Services.EventServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {

    @Autowired
    EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    UserRepositoryService userRepositoryService;
    @Autowired
    GetUserRepositoryService getUserRepositoryService;

    public Organizer getAndHandleNotOrganizerOfEvent(int id, Event event) {
        Organizer organizer = getUserRepositoryService.getOrganizerById(id);
        getAndHandleNotOrganizerOfEvent(organizer, event);
        return organizer;
    }

    private void getAndHandleNotOrganizerOfEvent(Organizer organizer, Event event) {
        Event eventFromDB = eventRepositoryServiceInterface.getById(event.getId());
        if (!eventFromDB.getEventOrganizer().equals(organizer))
            throw new NotOrganizerOfThisEventException();
    }
}
