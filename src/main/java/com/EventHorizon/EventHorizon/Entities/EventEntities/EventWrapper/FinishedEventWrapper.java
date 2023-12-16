package com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidateException;
import lombok.Data;

import java.util.Date;


@Data
public class FinishedEventWrapper extends EventWrapper{
    public FinishedEventWrapper(LaunchedEvent launchedEvent){
        if (launchedEvent.getEventDate().after(new Date()))
            throw new InvalidateException();

        this.launchedEvent=launchedEvent;
    }
}
