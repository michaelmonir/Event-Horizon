package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class EventWrapperFactory {

    public EventWrapper getEventWrapper(LaunchedEvent launchedEvent) {
        if (launchedEvent.getEventDate().before(new Date()))
            return new FinishedEventWrapper(launchedEvent);
        else return new FutureEventWrapper(launchedEvent);
    }
}
