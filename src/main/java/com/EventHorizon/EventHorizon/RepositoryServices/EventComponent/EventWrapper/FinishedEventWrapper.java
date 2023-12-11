package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidateException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
public class FinishedEventWrapper extends EventWrapper{
    protected FinishedEventWrapper(LaunchedEvent launchedEvent){
        if (launchedEvent.getEventDate().after(new Date()))
            throw new InvalidateException();

        this.launchedEvent=launchedEvent;
    }
}
