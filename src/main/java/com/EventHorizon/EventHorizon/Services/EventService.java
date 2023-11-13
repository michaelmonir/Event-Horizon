package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.ViewEventDTO;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.DTOs.DetailedEventDTO;
import com.EventHorizon.EventHorizon.Exceptions.*;
import com.EventHorizon.EventHorizon.Repository.*;
import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class EventService
{
    private OrganizerRepository organizerRepository = new OrganizerRepositoryMock();
    @Autowired
    private EventRepositoryService eventRepositoryService;

    public ViewEventDTO getEventForUser(int eventId) {
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);

        return new ViewEventDTO(event);
    }

    public DetailedEventDTO createEvent(int organizerId, DetailedEventDTO eventDTO) {
        ////////// expecting repository to return userNotFoundException if user is not found
        Organizable organizer = this.organizerRepository.findById(organizerId);
        Event event = eventDTO.getEvent();
        this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        return new DetailedEventDTO(event);
    }

    public DetailedEventDTO updateEvent(int organizerId, int id, DetailedEventDTO eventDTO) {
        Organizable organizer = this.organizerRepository.findById(organizerId);
        Event event = eventDTO.getEvent();
        this.checkAndHandleNotOrganizerOfEvent(organizer, event);

        event = this.eventRepositoryService.updateEventAndHandleNotFound(id, event);
        return new DetailedEventDTO(event);
    }

    public DetailedEventDTO getEventForOrganizer(int organizerId, int eventId) {
        Organizable organizer = this.organizerRepository.findById(organizerId);
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
        this.checkAndHandleNotOrganizerOfEvent(organizer, event);

        return new DetailedEventDTO(event);
    }

    // should handle this in database when merging
    private void checkAndHandleNotOrganizerOfEvent(Organizable organizer, Event event) {
        if (false)
            throw new NotOrganizerOfThisEventException();
    }
}
