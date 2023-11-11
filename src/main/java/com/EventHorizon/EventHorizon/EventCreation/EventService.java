package com.EventHorizon.EventHorizon.EventCreation;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class EventService {
    @Autowired
    private EventRepositry eventRepositry;
    public void saveEvent(Event event){
        eventRepositry.save(event);
    }
    public void updateEvent(int id, Event newEvent){
        Optional<Event> optionalOldEvent=eventRepositry.findById(id);
        if(!optionalOldEvent.isPresent())
            throw new EventNotFoundException();

        newEvent.setId(id);
        eventRepositry.save(newEvent);

    }

}

