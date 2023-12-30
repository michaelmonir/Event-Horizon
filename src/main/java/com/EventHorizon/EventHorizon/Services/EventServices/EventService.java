package com.EventHorizon.EventHorizon.Services.EventServices;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventCreationUpdationDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedDraftedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedEventDtoMapperInterface;
import com.EventHorizon.EventHorizon.Mappers.DraftedLaunchedEventMapper;
import com.EventHorizon.EventHorizon.Mappers.EventCreationUpdationDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.OrganizerInformationRepositoryService;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedLaunchedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.ViewEventDtoMapper;
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
    private InformationRepositoryService informationRepositoryService;
    @Autowired
    private ViewEventDtoMapper viewEventDtoMapper;
    @Autowired
    private OrganizerInformationRepositoryService organizerInformationService;
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
    private EventCreationUpdationDtoMapper eventCreationUpdationDtoMapper;

    public DetailedEventDto getEventForUser(int eventId) {
        try {
            LaunchedEvent event = this.launchedEventRepositoryService.getByIdAndHandleNotFound(eventId);
            return detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(event);
        } catch (Exception e) {
            DraftedEvent event = this.draftedEventRepositoryService.getByIdAndHandleNotFound(eventId);
            return detailedDraftedEventDtoMapper.getDTOfromDetailedEvent(event);
        }
    }

    public DetailedEventDto getEventForOrganizer(int informationId, int eventId, EventType eventType) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);

        Event event = eventRepositoryServiceInterface.getByIdAndEventType(eventId, eventType);
        userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
        return detailedEventDtoMapperInterface.getDTOfromDetailedEvent(event);
    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

    public DetailedEventDto createEvent(int informationId, EventCreationUpdationDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);

        DraftedEvent event = eventCreationUpdationDtoMapper.getEventForUpdating(eventDTO);

        event.setEventOrganizer(organizer);
        draftedEventRepositoryService.saveWhenCreating(event);
        return detailedEventDtoMapperInterface.getDTOfromDetailedEvent(event);
    }

    public DetailedEventDto updateEvent(int informationId, DetailedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);

        Event event = eventRepositoryServiceInterface.getById(eventDTO.getId());
        userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);

        detailedEventDtoMapperInterface.updateEventFromDetailedEventDTO(event, eventDTO);
        eventRepositoryServiceInterface.update(event);

        return detailedEventDtoMapperInterface.getDTOfromDetailedEvent(event);
    }

    public DetailedEventDto launchEvent(int informationId, int eventId) {
        DraftedEvent draftedEvent = draftedEventRepositoryService.getByIdAndHandleNotFound(eventId);
        this.checkAndHandleNotOrganizerOfEvent(informationId, draftedEvent);
        draftedEventRepositoryService.delete(eventId);

        LaunchedEvent launchedEvent = draftedLaunchedEventMapper.getLaunchedEventFromDraftedEvent(draftedEvent);
        launchedEventRepositoryService.saveWhenLaunching(launchedEvent);

        return detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(launchedEvent);
    }

    public void deleteEvent(int informationId, int eventId, EventType eventType) {
        Event event = eventRepositoryServiceInterface.getByIdAndEventType(eventId, eventType);

        this.checkAndHandleNotOrganizerOfEvent(informationId, event);
        eventRepositoryServiceInterface.delete(eventId, eventType);
    }

    public Organizer getOrganizerFromInformationId(int inforamtionID) {
        Information information = informationRepositoryService.getByID(inforamtionID);
        return (Organizer) organizerInformationService.getUserByInformation(information);
    }

    private void checkAndHandleNotOrganizerOfEvent(int informationId, Event event){
        Organizer organizer = getOrganizerFromInformationId(informationId);
        userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
    }
}