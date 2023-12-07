package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import org.springframework.stereotype.Service;

@Service
public class UserEventService {
    public void checkAndHandleNotOrganizerOfEvent(Organizer organizer, Event event) {
        if (!event.getEventOrganizer().equals(organizer))
            throw new NotOrganizerOfThisEventException();
    }
}
