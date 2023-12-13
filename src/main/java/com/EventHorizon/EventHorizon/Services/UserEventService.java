package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.SuperEventRepositoryService;
import jdk.jfr.EventFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {


    @Autowired
    EventRepositoryServiceFactory eventRepositoryServiceFactory;




    public void checkAndHandleNotOrganizerOfEvent(Organizer organizer, Event event) {
        SuperEventRepositoryService eventRepositoryService = eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(event.getEventType());
        Event eventFromDB = eventRepositoryService.getEventAndHandleNotFound(event.getId());
        if (!eventFromDB.getEventOrganizer().equals(organizer))
            throw new NotOrganizerOfThisEventException();
    }
}
