package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.SuperEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;

import java.util.Optional;

public interface SuperEventRepositoryService {
    public SuperEvent getEventAndHandleNotFound(int id);

    public SuperEvent saveEventWhenCreatingAndHandleAlreadyExisting(SuperEvent Event);

    public SuperEvent updateEventAndHandleNotFound(SuperEvent newEvent);

    public void deleteEvent(int id);

    public SuperEvent setEventOrganizer(Organizer organizer, SuperEvent superEvent);

    public Event getEventFromSuperEvent(SuperEvent superEvent);
}
