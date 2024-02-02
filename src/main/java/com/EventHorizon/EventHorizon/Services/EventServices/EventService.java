package com.EventHorizon.EventHorizon.Services.EventServices;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventCreationUpdationDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.DraftedLaunchedEventMapper;
import com.EventHorizon.EventHorizon.Mappers.EventCreationUpdationDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private DashboardRepositoryService dashboardRepositoryService;
    @Autowired
    private UserEventService userEventService;
    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;
    @Autowired
    private DraftedLaunchedEventMapper draftedLaunchedEventMapper;
    @Autowired
    UserRepositoryService userRepositoryService;
    private EventCreationUpdationDtoMapper eventCreationUpdationDtoMapper;
    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;

    public EventViewDto getEventForUser(int eventId) {
        Event event = eventRepositoryServiceInterface.getById(eventId);
        return eventViewDtoMapper.getDetailedEventFromEventDto(event);
    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

    public EventViewDto createEvent(int id, EventCreationUpdationDto eventDTO) {
        Organizer organizer = userRepositoryService.getOrganizerById(id);

        DraftedEvent event = eventCreationUpdationDtoMapper.getEventFromDtoForCreating(eventDTO);
        event.setEventOrganizer(organizer);
        draftedEventRepositoryService.saveWhenCreating(event);
        return eventViewDtoMapper.getDetailedEventFromEventDto(event);
    }

    public EventViewDto updateEvent(int id, EventCreationUpdationDto eventDTO) {
        Event event = eventRepositoryServiceInterface.getById(eventDTO.getId());
        userEventService.getAndHandleNotOrganizerOfEvent(id, event);

        eventCreationUpdationDtoMapper.updateEventAttributesFromDto(event, eventDTO);
        eventRepositoryServiceInterface.update(event);

        return eventViewDtoMapper.getDetailedEventFromEventDto(event);
    }

    public EventViewDto launchEvent(int id, int eventId) {
        DraftedEvent draftedEvent = draftedEventRepositoryService.getByIdAndHandleNotFound(eventId);
        userEventService.getAndHandleNotOrganizerOfEvent(id, draftedEvent);
        draftedEventRepositoryService.delete(eventId);
        LaunchedEvent launchedEvent = draftedLaunchedEventMapper.getLaunchedEventFromDraftedEvent(draftedEvent);
        launchedEventRepositoryService.saveWhenLaunching(launchedEvent);
        return eventViewDtoMapper.getDetailedEventFromEventDto(launchedEvent);
    }

    public void deleteEvent(int id, int eventId, EventType eventType) {
        Event event = eventRepositoryServiceInterface.getByIdAndEventType(eventId, eventType);
        userEventService.getAndHandleNotOrganizerOfEvent(id, event);
        eventRepositoryServiceInterface.delete(eventId, eventType);
    }
}