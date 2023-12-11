package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import lombok.Data;

@Data
public abstract class  EventWrapper {
    protected LaunchedEvent launchedEvent;
}
