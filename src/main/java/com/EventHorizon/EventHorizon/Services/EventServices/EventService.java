package com.EventHorizon.EventHorizon.Services.EventServices;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedDraftedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedEventDtoMapperInterface;
import com.EventHorizon.EventHorizon.Mappers.DraftedLaunchedEventMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedLaunchedEventDtoMapper;
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
    private DetailedLaunchedEventDtoMapper detailedLaunchedEventDtoMapper;
    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    private DetailedEventDtoMapperInterface detailedEventDtoMapperInterface;
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;
    @Autowired
    private DetailedDraftedEventDtoMapper detailedDraftedEventDtoMapper;
    @Autowired
    private DraftedLaunchedEventMapper draftedLaunchedEventMapper;
    @Autowired
    UserRepositoryService userRepositoryService;

    public DetailedEventDto getEventForUser(int eventId) {
        try {
            LaunchedEvent event = this.launchedEventRepositoryService.getByIdAndHandleNotFound(eventId);
            return detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(event);
        } catch (Exception e) {
            DraftedEvent event = this.draftedEventRepositoryService.getByIdAndHandleNotFound(eventId);
            return detailedDraftedEventDtoMapper.getDTOfromDetailedEvent(event);
        }
    }

    public DetailedEventDto getEventForOrganizer(int id, int eventId, EventType eventType) {
        Event event = eventRepositoryServiceInterface.getByIdAndEventType(eventId, eventType);
        userEventService.getAndHandleNotOrganizerOfEvent(id, event);
        return detailedEventDtoMapperInterface.getDTOfromDetailedEvent(event);
    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

    public DetailedEventDto createEvent(int id, DetailedEventDto eventDTO) {
        Organizer organizer = userRepositoryService.getOrganizerById(id);
        DraftedEvent event = detailedDraftedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        event.setEventOrganizer(organizer);
        draftedEventRepositoryService.saveWhenCreating(event);
        return detailedEventDtoMapperInterface.getDTOfromDetailedEvent(event);
    }

    public DetailedEventDto updateEvent(int id, DetailedEventDto eventDTO) {
        Event event = eventRepositoryServiceInterface.getById(eventDTO.getId());
        userEventService.getAndHandleNotOrganizerOfEvent(id, event);
        detailedEventDtoMapperInterface.updateEventFromDetailedEventDTO(event, eventDTO);
        eventRepositoryServiceInterface.update(event);

        return detailedEventDtoMapperInterface.getDTOfromDetailedEvent(event);
    }

    public DetailedEventDto launchEvent(int id, int eventId) {
        DraftedEvent draftedEvent = draftedEventRepositoryService.getByIdAndHandleNotFound(eventId);
        userEventService.getAndHandleNotOrganizerOfEvent(id, draftedEvent);
        draftedEventRepositoryService.delete(eventId);
        LaunchedEvent launchedEvent = draftedLaunchedEventMapper.getLaunchedEventFromDraftedEvent(draftedEvent);
        launchedEventRepositoryService.saveWhenLaunching(launchedEvent);
        return detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(launchedEvent);
    }

    public void deleteEvent(int id, int eventId, EventType eventType) {
        Event event = eventRepositoryServiceInterface.getByIdAndEventType(eventId, eventType);
        userEventService.getAndHandleNotOrganizerOfEvent(id, event);
        eventRepositoryServiceInterface.delete(eventId, eventType);
    }


}