package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedDraftedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.DetailedDraftedEventDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.DetailedLaunchedEventDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.ViewEventDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.OrganizerInformationRepositoryService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;
    @Autowired
    private DashboardRepositoryService dashboardRepositoryService;
    @Autowired
    private UserEventService userEventService;
    @Autowired
    private DetailedLaunchedEventDtoMapper detailedLaunchedEventDtoMapper;
    @Autowired
    private DetailedDraftedEventDtoMapper detailedDraftedEventDtoMapper;
    @Autowired
    private InformationRepositoryService informationService;
    @Autowired
    private ViewEventDtoMapper viewEventDtoMapper;
    @Autowired
    private OrganizerInformationRepositoryService organizerInformationService;

    public ViewEventDto getEventForUser(int eventId) {
        LaunchedEvent event = this.launchedEventRepositoryService.getEventAndHandleNotFound(eventId);
        return viewEventDtoMapper.getDTOfromViewEvent(event);
    }

    public DetailedLaunchedEventDto getLaunchedEventForOrganizer(int informationId, int eventId) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);

        LaunchedEvent event = this.launchedEventRepositoryService.getEventAndHandleNotFound(eventId);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event.getEvent());

        DetailedLaunchedEventDto resultDTO = detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedDraftedEventDto getDraftedEventForOrganizer(int informationId, int eventId) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);

        DraftedEvent event = this.draftedEventRepositoryService.getEventAndHandleNotFound(eventId);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event.getEvent());

        DetailedDraftedEventDto resultDTO = detailedDraftedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

    public DetailedLaunchedEventDto createLaunchedEvent(int informationId, DetailedLaunchedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        LaunchedEvent event = this.detailedLaunchedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        event.setEventOrganizer(organizer);
        this.launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        DetailedLaunchedEventDto resultDTO = detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedDraftedEventDto createDraftedEvent(int informationId, DetailedDraftedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        DraftedEvent event = this.detailedDraftedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        event.setEventOrganizer(organizer);
        this.draftedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        DetailedDraftedEventDto resultDTO = detailedDraftedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }
    public DetailedLaunchedEventDto updateLaunchedEvent(int informationId, DetailedLaunchedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        LaunchedEvent event = this.detailedLaunchedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        event.setEventOrganizer(organizer);
        this.launchedEventRepositoryService.updateEventAndHandleNotFound(event);
        DetailedLaunchedEventDto resultDTO = detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedDraftedEventDto updateDraftedEvent(int informationId, DetailedDraftedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        DraftedEvent event = this.detailedDraftedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        event.setEventOrganizer(organizer);
        this.draftedEventRepositoryService.updateEventAndHandleNotFound(event);
        DetailedDraftedEventDto resultDTO = detailedDraftedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public void deleteLaunchedEvent(int informationId, int eventId) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        LaunchedEvent event = this.launchedEventRepositoryService.getEventAndHandleNotFound(eventId);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event.getEvent());
        this.launchedEventRepositoryService.deleteEvent(eventId);
    }
    public void deleteDraftedEvent(int informationId, int eventId) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        DraftedEvent event = this.draftedEventRepositoryService.getEventAndHandleNotFound(eventId);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event.getEvent());
        this.draftedEventRepositoryService.deleteEvent(eventId);
    }

    public Organizer getOrganizerFromInformationId(int inforamtionID) {
        Information information = informationService.getByID(inforamtionID);

        return (Organizer)organizerInformationService.getUserByInformation(information);
    }
}
