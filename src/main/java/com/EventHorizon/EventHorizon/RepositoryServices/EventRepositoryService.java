package com.EventHorizon.EventHorizon.RepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EventRepositoryService {
    @Autowired
    private EventRepositry eventRepositry;

    public Event getEventAndHandleNotFound(int id) {
        Optional<Event> optionalOldEvent=eventRepositry.findById(id);
        if(!optionalOldEvent.isPresent())
            throw new EventNotFoundException();
        return optionalOldEvent.get();
    }

    public Event saveEventWhenCreatingAndHandleAlreadyExisting(Event event){
        if (event.getId() != 0) throw new EventAlreadyExisting();

        eventRepositry.save(event);
        return event;
    }

    public Event updateEventAndHandleNotFound(int id, Event newEvent){
        this.getEventAndHandleNotFound(id);

        newEvent.setId(id);
        eventRepositry.save(newEvent);
        return newEvent;
    }
}

