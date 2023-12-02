package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Exceptions.UserNotAnOrganizerException;
import com.EventHorizon.EventHorizon.RepositoryServices.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.DelaitedEventDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.ViewEventDtoMapper;
import com.EventHorizon.EventHorizon.Services.InformationServiceComponent.InformationServiceFactory;
import com.EventHorizon.EventHorizon.Services.InformationServiceComponent.OrganizerInformationService;
import com.EventHorizon.EventHorizon.Services.InformationServiceComponent.UserInformationService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private DashboardRepositoryService dashboardRepositoryService;
    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private UserEventService userEventService;
    @Autowired
    private DelaitedEventDtoMapper delaitedEventDtoMapper;
    @Autowired
    private InformationService informationService;
    @Autowired
    private ViewEventDtoMapper viewEventDtoMapper;

    public ViewEventDto getEventForUser(int eventId) {
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);

        return viewEventDtoMapper.getDTOfromViewEvent(event);
    }

    public DetailedEventDto getEventForOrganizer(int informationId, int eventId) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);

        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);

        DetailedEventDto resultDTO = delaitedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public List<EventHeaderDto> getEventHeadersList(int pageIndex, int pageSize) {
        return this.dashboardRepositoryService.getPage(pageIndex, pageSize);
    }

    public Organizer getOrganizerFromInformationId(int inforamtionID) {
        Information information = informationService.getByID(inforamtionID);

        OrganizerInformationService organizerInformationService = new OrganizerInformationService();
        User user = organizerInformationService.getUserByInformation(information);

        return (Organizer)user;
    }

    public DetailedEventDto createEvent(int informationId, DetailedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        Event event = this.delaitedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        event.setEventOrganizer(organizer);
        this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        DetailedEventDto resultDTO = delaitedEventDtoMapper.getDTOfromDetailedEvent(event);

        return resultDTO;
    }

    public DetailedEventDto updateEvent(int informationId, int id, DetailedEventDto eventDTO) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        Event event = this.delaitedEventDtoMapper.getEventFromDetailedEventDTO(eventDTO);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);

        event = this.eventRepositoryService.updateEventAndHandleNotFound(id, event);

        DetailedEventDto resultDTO = delaitedEventDtoMapper.getDTOfromDetailedEvent(event);
        return resultDTO;
    }

    public void deleteEvent(int informationId, int eventId) {
        Organizer organizer = this.getOrganizerFromInformationId(informationId);
        Event event = this.eventRepositoryService.getEventAndHandleNotFound(eventId);
        this.userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
        this.eventRepositoryService.deleteEvent(eventId);
    }
}
