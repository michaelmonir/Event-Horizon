package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventRepositoryServiceInterface
{
    @Autowired
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;
    @Autowired
    private EventRepositoryService eventRepositoryService;

    public Event getById(int id) {
        return eventRepositoryService.getById(id);
    }

    public Event saveWhenCreating(Event Event) {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(Event.getEventType());
        return eventRepositoryService.saveWhenCreating(Event);
    }

    public Event update(Event newEvent) {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(newEvent.getEventType());
        return eventRepositoryService.update(newEvent);
    }

    public void delete(int id, EventType eventType) {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(eventType);
        eventRepositoryService.delete(id);
    }
}
