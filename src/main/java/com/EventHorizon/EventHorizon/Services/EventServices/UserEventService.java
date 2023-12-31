package com.EventHorizon.EventHorizon.Services.EventServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {

    @Autowired
    EventRepositoryServiceInterface eventRepositoryServiceInterface;

    public void checkAndHandleNotOrganizerOfEvent(Organizer organizer, Event event) {
        Event eventFromDB = eventRepositoryServiceInterface.getByIdAndEventType(event.getId(), event.getEventType());
         if (!eventFromDB.getEventOrganizer().equals(organizer))
            throw new NotOrganizerOfThisEventException();
    }
}
