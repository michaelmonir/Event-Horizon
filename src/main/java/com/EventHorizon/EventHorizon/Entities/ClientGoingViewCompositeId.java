package com.EventHorizon.EventHorizon.Entities;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;

import java.io.Serializable;

public class ClientGoingViewCompositeId implements Serializable {
    private int client;
    private Event event;
}
