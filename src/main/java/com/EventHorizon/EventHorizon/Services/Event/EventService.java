package com.EventHorizon.EventHorizon.Services.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventCreationUpdationDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Mappers.Event.DraftedLaunchedEventMapper;
import com.EventHorizon.EventHorizon.Mappers.Event.EventCreationUpdationDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Utility.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.DraftedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.EventRepositoryServiceFacadeImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.LaunchedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private LaunchedEventRepositoryServiceImpl launchedEventRepositoryServiceImpl;
    @Autowired
    private DashboardRepositoryService dashboardRepositoryService;
    @Autowired
    private UserEventService userEventService;
    @Autowired
    private EventRepositoryServiceFacadeImpl eventRepositoryServiceFacadeImpl;
    @Autowired
    private DraftedEventRepositoryServiceImpl draftedEventRepositoryServiceImpl;
    @Autowired
    private DraftedLaunchedEventMapper draftedLaunchedEventMapper;
    @Autowired
    UserRepositoryService userRepositoryService;
    @Autowired
    private EventCreationUpdationDtoMapper eventCreationUpdationDtoMapper;
    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;
    @Autowired
    private GetUserRepositoryService getUserRepositoryService;

    public EventViewDto createEvent(int id, EventCreationUpdationDto eventDTO) {
        Organizer organizer = getUserRepositoryService.getOrganizerById(id);

        DraftedEvent event = eventCreationUpdationDtoMapper.getEventFromDtoForCreating(eventDTO);
        event.setEventOrganizer(organizer);
        draftedEventRepositoryServiceImpl.saveWhenCreating(event);
        return eventViewDtoMapper.getDetailedDtoFromEvent(event);
    }

    public EventViewDto updateEvent(int id, EventCreationUpdationDto eventDTO) {
        Event event = eventRepositoryServiceFacadeImpl.getById(eventDTO.getId());
        userEventService.checkOrganizerOfEvent(id, event);

        eventCreationUpdationDtoMapper.updateEventAttributesFromDto(event, eventDTO);
        eventRepositoryServiceFacadeImpl.update(event);

        return eventViewDtoMapper.getDetailedDtoFromEvent(event);
    }

    @Transactional
    public EventViewDto launchEvent(int id, int eventId) {
        DraftedEvent draftedEvent = draftedEventRepositoryServiceImpl.getById(eventId);

        userEventService.checkOrganizerOfEvent(id, draftedEvent);
        eventRepositoryServiceFacadeImpl.delete(eventId);

        LaunchedEvent launchedEvent = draftedLaunchedEventMapper.getLaunchedEventFromDraftedEvent(draftedEvent);
        launchedEventRepositoryServiceImpl.saveWhenLaunching(launchedEvent);

        return eventViewDtoMapper.getDetailedDtoFromEvent(launchedEvent);
    }

    public void deleteEvent(int id, int eventId) {
        Event event = eventRepositoryServiceFacadeImpl.getById(eventId);
        userEventService.checkOrganizerOfEvent(id, event);
        eventRepositoryServiceFacadeImpl.delete(eventId);
    }
}