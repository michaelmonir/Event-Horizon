package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

@Service
public class EventRepositoryServiceFacade {
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
