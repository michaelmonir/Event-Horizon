package com.EventHorizon.EventHorizon.Entities.Event.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions.NotFutureEventException;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import lombok.Data;

@Data
public class FutureEventWrapper extends EventWrapper{

    public FutureEventWrapper(LaunchedEvent launchedEvent) {
        if (DateFunctions.isDateBeforeNow(launchedEvent.getEventDate()))
            throw new NotFutureEventException();

        this.launchedEvent = launchedEvent;
    }
}
