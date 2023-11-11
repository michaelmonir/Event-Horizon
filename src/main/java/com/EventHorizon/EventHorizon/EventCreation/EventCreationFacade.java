package com.EventHorizon.EventHorizon.EventCreation;

import com.EventHorizon.EventHorizon.EventCreation.EventService.EventService;
import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;
import org.springframework.beans.factory.annotation.Autowired;

public class EventCreationFacade
{
    private Organizable organizer;
    @Autowired
    private EventService eventService;

    public EventCreationFacade(Organizable organizer) {
        this.organizer = organizer;
    }

    public void updateEvent(int id, EventDTO eventDTO)
    {
        Event event = eventDTO.getEvent();
        this.eventService.updateEvent(id, event);
    }///////////////// handle try catch in the service api: case event doesn't exist

    public Event createEvent(EventDTO eventDTO)
    {
        Event event = eventDTO.getEvent();
        this.eventService.saveEventWhenCreating(event);
        return event;
    }///////////////////////
}
