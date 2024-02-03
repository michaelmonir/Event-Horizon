package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;

public interface SuperEventRepositoryService {
    public Event saveWhenCreating(Event Event);

    public Event update(Event newEvent);

    public void delete(int id);
}