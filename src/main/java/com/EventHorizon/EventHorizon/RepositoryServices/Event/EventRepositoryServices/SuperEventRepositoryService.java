package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event.Event;

public interface SuperEventRepositoryService {
    public Event update(Event newEvent);

    public void delete(int id);
}
