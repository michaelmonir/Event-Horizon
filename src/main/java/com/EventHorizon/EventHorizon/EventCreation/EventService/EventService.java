package com.EventHorizon.EventHorizon.EventCreation.EventService;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class EventService {
    @Autowired
    private EventRepositry eventRepositry;
    public Event saveEventWhenCreating(Event event){
        if (event.getId() != 0)
            throw new EventAlreadyExisting();

        eventRepositry.save(event);
        return event;
    }

    public Event updateEvent(int id, Event newEvent){
        Optional<Event> optionalOldEvent=eventRepositry.findById(id);

        if (newEvent.getId() != 0)
            throw new EventAlreadyExisting();

        if(!optionalOldEvent.isPresent())
            throw new EventNotFoundException();


        newEvent.setId(id);
        eventRepositry.save(newEvent);
        return newEvent;
    }
    public void deleteEvent(int id){
        Optional<Event> optionalOldEvent=eventRepositry.findById(id);

        if(!optionalOldEvent.isPresent())
            throw new EventNotFoundException();

        eventRepositry.deleteById(id);

    }

}
