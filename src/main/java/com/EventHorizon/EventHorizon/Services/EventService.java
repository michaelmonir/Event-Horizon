package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.RepositoryServices.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;;
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
    @Autowired
    private OrganizerService organizerService;
    @Autowired
    UserEventService userEventService;

    public ViewEventDto getEventForUser(int eventId) {
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);

        return new ViewEventDto(event);
    }

    public DetailedEventDto getEventForOrganizer(int organizerId, int eventId) {
        Organizer organizer = this.organizerService.getByID(organizerId);

        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);

        DetailedEventDto resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

    public DetailedEventDto createEvent(int organizerId, DetailedEventDto eventDTO) {
        Organizer organizer = this.organizerService.getByID(organizerId);
        Event event = this.eventRepositoryService.getEventFromDetailedEventDTO(eventDTO);
        this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        DetailedEventDto resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedEventDto updateEvent(int organizerId, int id, DetailedEventDto eventDTO) {
        Organizer organizer = this.organizerService.getByID(organizerId);

        Event event = this.eventRepositoryService.getEventFromDetailedEventDTO(eventDTO);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);

        event = this.eventRepositoryService.updateEventAndHandleNotFound(id, event);

        DetailedEventDto resultDTO = this.eventRepositoryService.getDTOfromDetailedEvent(event);
        return resultDTO;
    }

    public void deleteEvent(int organizerId, int eventId) {
        Organizer organizer = this.organizerService.getByID(organizerId);
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
        this.eventRepositoryService.deleteEvent(eventId);
    }
}
