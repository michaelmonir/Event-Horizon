package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class FinishedEventWrapper extends EventWrapper{
    public FinishedEventWrapper(LaunchedEvent launchedEvent){
        this.launchedEvent=launchedEvent;
    }
}
