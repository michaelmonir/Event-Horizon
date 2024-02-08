package com.EventHorizon.EventHorizon.Entities.Event.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventTypeExceptions.NotFinishedEventException;
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
