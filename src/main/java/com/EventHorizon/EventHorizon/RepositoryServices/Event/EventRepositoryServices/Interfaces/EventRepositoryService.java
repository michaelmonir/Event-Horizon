package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces;

import com.EventHorizon.EventHorizon.Entities.Event.Event;

public interface EventRepositoryService {
    Event getById(int id);
    Event update(Event newEvent);
    void delete(int id);
}
