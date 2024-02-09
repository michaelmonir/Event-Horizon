package com.EventHorizon.EventHorizon.Services.Event;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.Event.NotLaunchedEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.EventRepositoryServiceFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventTypeService
{
    public void checkFutureEvent(Event event) {
        this.checkLaunchedEvent(event);
        new FutureEventWrapper((LaunchedEvent) event);
    }

    private void checkLaunchedEvent(Event event) {
        if (!(event instanceof LaunchedEvent) || event.getEventType() != EventType.LAUNCHEDEVENT)
            throw new NotLaunchedEventException();
    }
}
