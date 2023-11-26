package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.DTOs.DetailedEventDto;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.Repository.*;
import com.EventHorizon.EventHorizon.RepositoryServices.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService
{
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private DashboardRepositoryService dashboardRepositoryService;

    private OrganizerRepository organizerRepository = new OrganizerRepositoryMock();

    public ViewEventDto getEventForUser(int eventId) {
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);

        return new ViewEventDto(event);
    }

    public DetailedEventDto getEventForOrganizer(int organizerId, int eventId) {
        Organizable organizer = this.organizerRepository.findById(organizerId);
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
        this.checkAndHandleNotOrganizerOfEvent(organizer, event);

        DetailedEventDto resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

    public DetailedEventDto createEvent(int organizerId, DetailedEventDto eventDTO) {
        ////////// expecting repository to return userNotFoundException if user is not found
        Organizable organizer = this.organizerRepository.findById(organizerId);
        Event event = this.eventRepositoryService.getEventFromDetailedEventDTO(eventDTO);

        this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        DetailedEventDto resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedEventDto updateEvent(int organizerId, int id, DetailedEventDto eventDTO) {
        Organizable organizer = this.organizerRepository.findById(organizerId);

        Event event = this.eventRepositoryService.getEventFromDetailedEventDTO(eventDTO);
        this.checkAndHandleNotOrganizerOfEvent(organizer, event);

        event = this.eventRepositoryService.updateEventAndHandleNotFound(id, event);

        DetailedEventDto resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);
        return resultDTO;
    }

    public void deleteEvent(int organizerId, int eventId) {
        Organizable organizer = this.organizerRepository.findById(organizerId);
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
        this.checkAndHandleNotOrganizerOfEvent(organizer, event);

        this.eventRepositoryService.deleteEvent(eventId);
    }

    // should handle this in database when merging
    private void checkAndHandleNotOrganizerOfEvent(Organizable organizer, Event event) {
        if (false)
            throw new NotOrganizerOfThisEventException();
    }

    private boolean userAllowedViewingListOfPages(int userId)
    {
        return true;
    }
}
