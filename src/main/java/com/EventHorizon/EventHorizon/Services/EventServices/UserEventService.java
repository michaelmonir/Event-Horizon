package com.EventHorizon.EventHorizon.Services.EventServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {

    @Autowired
    EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    UserRepositoryService userRepositoryService;

    public Organizer getAndHandleNotOrganizerOfEvent(int id, Event event) {
        Organizer organizer = userRepositoryService.getOrganizerById(id);
        getAndHandleNotOrganizerOfEvent(organizer, event);
        return organizer;
    }

    private void getAndHandleNotOrganizerOfEvent(Organizer organizer, Event event) {
        Event eventFromDB = eventRepositoryServiceInterface.getById(event.getId());
        if (!eventFromDB.getEventOrganizer().equals(organizer))
            throw new NotOrganizerOfThisEventException();
    }
}
