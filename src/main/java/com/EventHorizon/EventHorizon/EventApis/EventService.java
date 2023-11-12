package com.EventHorizon.EventHorizon.EventApis;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.EventCreationRepository;
import com.EventHorizon.EventHorizon.EventCreation.EventDTO;
import com.EventHorizon.EventHorizon.Exceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;
import com.EventHorizon.EventHorizon.Users.Organizer.OrganizerMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService
{
    private OrganizerRepository organizerRepository = new OrganizerRepositoryMock();
    @Autowired
    private EventCreationRepository eventCreationRepository;

    public Event createEvent(int organizerId, EventDTO eventDTO)
    {
        ////////// expecting repository to return userNotFoundException if user is not found
        Organizable organizer = this.organizerRepository.findById();
        Event event = eventDTO.getEvent();
        this.eventCreationRepository.saveEventWhenCreating(event);
        return event;
    }

    public void updateEvent(int organizerId, int id, EventDTO eventDTO)
    {
        Organizable organizer = this.organizerRepository.findById();
        Event event = eventDTO.getEvent();

        if (!this.isOrganizerOfEvent(organizer, event))
            throw new NotOrganizerOfThisEventException();
        this.eventCreationRepository.updateEvent(id, event);
    }

    // should handle this in database when merging
    private boolean isOrganizerOfEvent(Organizable organizer, Event event)
    {
        return true;
    }
}
