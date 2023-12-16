package com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidDateException;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import lombok.Data;

@Data
public class FutureEventWrapper extends EventWrapper{

    public FutureEventWrapper(LaunchedEvent launchedEvent) {
        if (DateFunctions.isDateBeforeNow(launchedEvent.getEventDate()))
            throw new InvalidDateException();

        this.launchedEvent = launchedEvent;
    }
}
