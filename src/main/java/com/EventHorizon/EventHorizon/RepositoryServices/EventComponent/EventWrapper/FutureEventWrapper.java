package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidateException;
import lombok.Data;

import java.util.Date;

@Data
public class FutureEventWrapper extends EventWrapper{
    public FutureEventWrapper(LaunchedEvent launchedEvent){
        if (launchedEvent.getEventDate().before(new Date()))
            throw new InvalidateException();

        this.launchedEvent=launchedEvent;
    }
}
