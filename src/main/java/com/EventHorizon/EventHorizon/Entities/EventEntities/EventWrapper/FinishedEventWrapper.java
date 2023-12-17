package com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions.NotFinishedEventException;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import lombok.Data;

@Data
public class FinishedEventWrapper extends EventWrapper{
    public FinishedEventWrapper(LaunchedEvent launchedEvent){
        if (DateFunctions.isDateAfterNow(launchedEvent.getEventDate()))
            throw new NotFinishedEventException();

        this.launchedEvent=launchedEvent;
    }
}
