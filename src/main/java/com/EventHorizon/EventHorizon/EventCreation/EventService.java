package com.EventHorizon.EventHorizon.EventCreation;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class EventService {
    @Autowired
    private EventRepositry eventRepositry;
    public void saveEvent(Event event){
        eventRepositry.save(event);
    }
    public boolean updateEvent(int id, Event newEvent){
        Optional<Event> optionalOldEvent=eventRepositry.findById(id);
        if(optionalOldEvent.isPresent()){
            Event oldEvent= optionalOldEvent.get();
            oldEvent.setEventCategory(newEvent.getEventCategory());
            oldEvent.setEventAds(newEvent.getEventAds());
            oldEvent.setEventDate(newEvent.getEventDate());
            oldEvent.setEventLocation(newEvent.getEventLocation());
            oldEvent.setDescription(newEvent.getDescription());
            oldEvent.setName(newEvent.getName());
            eventRepositry.save(oldEvent);
            return true;
        }
        else {
            return false;
        }
    }

}
