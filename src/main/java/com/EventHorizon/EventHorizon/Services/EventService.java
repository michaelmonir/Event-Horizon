package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.ViewEventDTO;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.DTOs.DetailedEventDTO;
import com.EventHorizon.EventHorizon.Exceptions.*;
import com.EventHorizon.EventHorizon.Repository.*;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class EventService
{
    @Autowired
    private EventRepositoryService eventRepositoryService;

    private OrganizerRepository organizerRepository = new OrganizerRepositoryMock();

    public ViewEventDTO getEventForUser(int eventId) {
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);

        return new ViewEventDTO(event);
    }

    public DetailedEventDTO getEventForOrganizer(int organizerId, int eventId) {
        Organizable organizer = this.organizerRepository.findById(organizerId);
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
        this.checkAndHandleNotOrganizerOfEvent(organizer, event);

        DetailedEventDTO resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedEventDTO createEvent(int organizerId, DetailedEventDTO eventDTO) {
        ////////// expecting repository to return userNotFoundException if user is not found
        Organizable organizer = this.organizerRepository.findById(organizerId);
        Event event = this.eventRepositoryService.getEventFromDetailedEventDTO(eventDTO);

        this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        DetailedEventDTO resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedEventDTO updateEvent(int organizerId, int id, DetailedEventDTO eventDTO) {
        Organizable organizer = this.organizerRepository.findById(organizerId);

        Event event = this.eventRepositoryService.getEventFromDetailedEventDTO(eventDTO);
        this.checkAndHandleNotOrganizerOfEvent(organizer, event);

        event = this.eventRepositoryService.updateEventAndHandleNotFound(id, event);

        DetailedEventDTO resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);
        return resultDTO;
    }

    // should handle this in database when merging
    private void checkAndHandleNotOrganizerOfEvent(Organizable organizer, Event event) {
        if (false)
            throw new NotOrganizerOfThisEventException();
    }
}
