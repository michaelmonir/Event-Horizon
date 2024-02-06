package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRepositoryServiceInterface {
    @Autowired
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;
    @Autowired
    private EventRepositoryService eventRepositoryService;

    public Event getByIdAndHandleNotFound(int id) {
        return eventRepositoryService.getByIdAndHandleNotFound(id);
    }

    public Event update(Event newEvent) {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(newEvent.getEventType());
        return eventRepositoryService.update(newEvent);
    }

    public void delete(int id) {
        this.eventRepositoryService.delete(id);
    }
}
