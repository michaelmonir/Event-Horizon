package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRepositoryServiceInterface
{
    @Autowired
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;

    public Event getByIdAndEventType(int id, EventType eventType)
    {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(eventType);
        return eventRepositoryService.getById(id);
    }

    public Event getById(int id)
    {
        try {
            SuperEventRepositoryService eventRepositoryService
                    = eventRepositoryServiceFactory.getByEventType(EventType.LAUNCHEDEVENT);
            return eventRepositoryService.getById(id);
        } catch (Exception e) {
            SuperEventRepositoryService eventRepositoryService
                    = eventRepositoryServiceFactory.getByEventType(EventType.DRAFTEDEVENT);
            return eventRepositoryService.getById(id);
        }
    }

    public Event saveWhenCreating(Event Event)
    {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(Event.getEventType());
        return eventRepositoryService.saveWhenCreating(Event);
    }

    public Event update(Event newEvent)
    {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(newEvent.getEventType());
        return eventRepositoryService.update(newEvent);
    }

    public void delete(int id, EventType eventType)
    {
        SuperEventRepositoryService eventRepositoryService
                = eventRepositoryServiceFactory.getByEventType(eventType);
        eventRepositoryService.delete(id);
    }
}
