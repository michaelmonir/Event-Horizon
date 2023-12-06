package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
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
    private InformationRepositoryService informationService;
    @Autowired
    private ViewEventDtoMapper viewEventDtoMapper;
    @Autowired
    private OrganizerInformationRepositoryService organizerInformationService;

//    public ViewEventDto getEventForUser(int eventId) {
//        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
//
//        return viewEventDtoMapper.getDTOfromViewEvent(event);
//    }

//    public DetailedLaunchedEventDto getEventForOrganizer(int informationId, int eventId) {
//        Organizer organizer = this.getOrganizerFromInformationId(informationId);
//
//        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
//        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
//
//        DetailedLaunchedEventDto resultDTO = detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(event);
//
//        return resultDTO;
//    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

//    public DetailedLaunchedEventDto createLaunchedEvent(int informationId, DetailedLaunchedEventDto eventDTO) {
//        Organizer organizer = this.getOrganizerFromInformationId(informationId);
//        Event event = this.delaitedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
//        event.setEventOrganizer(organizer);
//        this.launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
//        DetailedLaunchedEventDto resultDTO = delaitedEventDtoMapper.getDTOfromDetailedEvent(event);
//
//        return resultDTO;
//    }
//
//    public DetailedLaunchedEventDto updateEvent(int informationId, DetailedLaunchedEventDto eventDTO) {
//        Organizer organizer = this.getOrganizerFromInformationId(informationId);
//        Event event = this.delaitedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
//        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
//
//        event = this.eventRepositoryService.updateEventAndHandleNotFound(event);
//
//        DetailedLaunchedEventDto resultDTO = delaitedEventDtoMapper.getDTOfromDetailedEvent(event);
//        return resultDTO;
//    }
//
//    public void deleteEvent(int informationId, int eventId) {
//        Organizer organizer = this.getOrganizerFromInformationId(informationId);
//        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
//        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
//        this.eventRepositoryService.deleteEvent(eventId);
//    }

    public Organizer getOrganizerFromInformationId(int inforamtionID) {
        Information information = informationService.getByID(inforamtionID);

        return (Organizer)organizerInformationService.getUserByInformation(information);
    }
}
