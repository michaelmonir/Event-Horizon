package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces.EventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRepositoryServiceFacadeImpl implements EventRepositoryService {
    @Autowired
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;
    @Autowired
    private CommonEventRepositoryService commonEventRepositoryService;

    public Event getById(int id) {
        return commonEventRepositoryService.getByIdAndHandleNotFound(id);
    }

    public Event update(Event newEvent) {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(newEvent.getEventType());
        return eventRepositoryService.update(newEvent);
    }

    public void delete(int id) {
        this.commonEventRepositoryService.delete(id);
    }
}
