package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;

public interface SuperEventRepositoryService {
    public Event getEventAndHandleNotFound(int id);

    public Event saveEventWhenCreatingAndHandleAlreadyExisting(Event Event);

    public Event updateEventAndHandleNotFound(Event newEvent);

    public void deleteEvent(int id);

}
