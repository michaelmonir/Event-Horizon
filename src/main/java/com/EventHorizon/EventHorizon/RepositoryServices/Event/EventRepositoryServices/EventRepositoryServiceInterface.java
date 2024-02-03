package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRepositoryServiceInterface {
    @Autowired
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;

    public Event getById(int id) {
        return null;
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
