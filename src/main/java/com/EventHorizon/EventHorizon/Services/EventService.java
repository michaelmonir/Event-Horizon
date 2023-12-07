package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.SuperEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.*;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.*;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.OrganizerInformationRepositoryService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private DetailedEventDtoMapperFactory detailedEventDtoMapperFactory;
    @Autowired
    private DashboardRepositoryService dashboardRepositoryService;
    @Autowired
    private UserEventService userEventService;

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

    public DetailedEventDto getEventForOrganizer(int informationId, int eventId,EventType eventType) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        DetailedEventDtoMapper detailedEventDtoMapper=detailedEventDtoMapperFactory.getEventDtoMapperByEventType(eventType);
        SuperEventRepositoryService eventRepositoryService=eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(eventType);
        SuperEvent event = eventRepositoryService.getEventAndHandleNotFound(eventId);
        Event eventInformation =eventRepositoryService.getEventFromSuperEvent(event);
        userEventService.checkAndHandleNotOrganizerOfEvent(organizer, eventInformation);
        DetailedEventDto resultDTO = detailedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

    public DetailedEventDto createEvent(int informationId, DetailedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        EventType eventType=eventDTO.getEventType();
        DetailedEventDtoMapper detailedEventDtoMapper=detailedEventDtoMapperFactory.getEventDtoMapperByEventType(eventType);
        SuperEventRepositoryService eventRepositoryService=eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(eventType);
        SuperEvent event = detailedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        event=eventRepositoryService.setEventOrganizer(organizer,event);
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        DetailedEventDto resultDTO = detailedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedEventDto updateEvent(int informationId, DetailedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        EventType eventType=eventDTO.getEventType();
        DetailedEventDtoMapper detailedEventDtoMapper=detailedEventDtoMapperFactory.getEventDtoMapperByEventType(eventType);
        SuperEventRepositoryService eventRepositoryService=eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(eventType);
        SuperEvent event = detailedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        Event eventInformation =eventRepositoryService.getEventFromSuperEvent(event);
        userEventService.checkAndHandleNotOrganizerOfEvent(organizer, eventInformation);
        event = eventRepositoryService.updateEventAndHandleNotFound(event);
        DetailedEventDto resultDTO = detailedEventDtoMapper.getDTOfromDetailedEvent(event);
        return resultDTO;
    }

    public void deleteEvent(int informationId, DetailedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        EventType eventType=eventDTO.getEventType();
        SuperEventRepositoryService eventRepositoryService=eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(eventType);
        SuperEvent event = eventRepositoryService.getEventAndHandleNotFound(eventDTO.getId());
        Event eventInformation =eventRepositoryService.getEventFromSuperEvent(event);
        userEventService.checkAndHandleNotOrganizerOfEvent(organizer, eventInformation);
        eventRepositoryService.deleteEvent(eventDTO.getId());
    }

    public Organizer getOrganizerFromInformationId(int inforamtionID) {
        Information information = informationService.getByID(inforamtionID);

        return (Organizer) organizerInformationService.getUserByInformation(information);
    }


}