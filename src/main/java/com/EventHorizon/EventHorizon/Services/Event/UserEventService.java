package com.EventHorizon.EventHorizon.Services.Event;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Entities.User.User;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceFacade;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {

    @Autowired
    EventRepositoryServiceFacade eventRepositoryServiceFacade;
    @Autowired
    UserRepositoryService userRepositoryService;
    @Autowired
    GetUserRepositoryService getUserRepositoryService;

    public Organizer checkOrganizerOfEvent(int id, Event event) {
        Organizer organizer = getUserRepositoryService.getOrganizerById(id);
        checkOrganizerOfEvent(organizer, event);
        return organizer;
    }

    public void checkOrganizerOfEvent(User user, Event event) {
        if (!event.getEventOrganizer().equals(user))
            throw new NotOrganizerOfThisEventException();
    }
}
