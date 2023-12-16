package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.SuperEventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {


    @Autowired
    EventRepositoryServiceFactory eventRepositoryServiceFactory;




    public void checkAndHandleNotOrganizerOfEvent(Organizer organizer, Event event) {
        SuperEventRepositoryService eventRepositoryService = eventRepositoryServiceFactory.getByEventType(event.getEventType());
        Event eventFromDB = eventRepositoryService.getById(event.getId());
        if (!eventFromDB.getEventOrganizer().equals(organizer))
            throw new NotOrganizerOfThisEventException();
    }
}
