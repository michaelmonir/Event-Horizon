package com.EventHorizon.EventHorizon.Entities.Event.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import lombok.Data;

@Data
public abstract class  EventWrapper {
    protected LaunchedEvent launchedEvent;
}
