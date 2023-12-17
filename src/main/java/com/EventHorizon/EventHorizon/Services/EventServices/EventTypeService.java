package com.EventHorizon.EventHorizon.Services.EventServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotLaunchedEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService
{
    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;

    public void checkAndHandleLaunchedEvent(int eventId, EventType eventType) {
        Event event = this.eventRepositoryServiceInterface.getByIdAndEventType(eventId, eventType);
        this.checkAndHandleLaunchedEvent(event);
    }

    public void checkAndHandleSeatTypeOfLaunchedFutureEvent(int eventId, EventType eventType) {
        Event event = this.eventRepositoryServiceInterface.getByIdAndEventType(eventId, eventType);
        this.checkAndHandleLaunchedEvent(event);

        FutureEventWrapper futureEventWrapper = new FutureEventWrapper((LaunchedEvent) event);
    }

    private void checkAndHandleLaunchedEvent(Event event) {
        if (!(event instanceof LaunchedEvent) || event.getEventType() != EventType.LAUNCHEDEVENT)
            throw new NotLaunchedEventException();
    }
}
