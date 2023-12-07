package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import lombok.Data;

@Data
public class FutureEventWrapper extends EventWrapper{
    protected FutureEventWrapper(LaunchedEvent launchedEvent){
        this.launchedEvent=launchedEvent;
    }
}
