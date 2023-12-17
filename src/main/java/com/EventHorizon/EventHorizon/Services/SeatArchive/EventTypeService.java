package com.EventHorizon.EventHorizon.Services.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions.NotFutureEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService
{
    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;

    public boolean checkSeatTypeOfLaunchedFutureEvent(int eventId, EventType eventType) {
        Event event = this.eventRepositoryServiceInterface.getByIdAndEventType(eventId, eventType);
        if (!(event instanceof LaunchedEvent))
            return false;

        try {
            FutureEventWrapper futureEventWrapper = new FutureEventWrapper((LaunchedEvent) event);
            return true;
        }
        catch (NotFutureEventException e) {
            return false;
        }
    }
}
