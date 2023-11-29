package com.EventHorizon.EventHorizon.ServiceTests;

import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.DelaitedEventDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.ViewEventDtoMapper;
import com.EventHorizon.EventHorizon.Services.EventService;
import com.EventHorizon.EventHorizon.Services.InformationService;
import com.EventHorizon.EventHorizon.Services.OrganizerService;
import com.EventHorizon.EventHorizon.Services.UserEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventServiceTest
{
    @Mock
    private EventRepositoryService eventRepositoryService;
    @Mock
    private UserEventService userEventService;
    @Mock
    private OrganizerService organizerService;
    @Mock
    private DelaitedEventDtoMapper delaitedEventDtoMapper;
    @Mock
    private ViewEventDtoMapper viewEventDtoMapper;
    @Mock
    private InformationService informationService;
    @InjectMocks
    private EventService eventService;

    private Event customEvent;
    private ViewEventDto customViewEventDto;
    private DetailedEventDto customDetailedEventDto;
    private Organizer customOrganizer;
    private Information customInformation;

    @Test
    public void gettingEventForUser() {
        this.initializeMocksAndCustomDTOs();

        ViewEventDto viewEventDTO = this.eventService.getEventForUser(1);
        ViewEventDto expectedViewEventDto = this.customViewEventDto;

        Assertions.assertTrue(viewEventDTO.equals(expectedViewEventDto));
    }

    @Test
    public void gettingOrganizerEvent() {
        this.initializeMocksAndCustomDTOs();
        this.eventService.getEventForUser(1);
        DetailedEventDto detailedEventDTO = this.eventService.getEventForOrganizer(1, 1);
        DetailedEventDto expectedViewEventDTO = this.customDetailedEventDto;

        Assertions.assertTrue(expectedViewEventDTO.equals(detailedEventDTO));
    }

    @Test
    public void createEvent() {
        this.initializeMocksAndCustomDTOs();

        DetailedEventDto detailedEventDTO = this.customDetailedEventDto;
        DetailedEventDto resultEventDTO = this.eventService.createEvent(1, detailedEventDTO);

        Assertions.assertTrue(detailedEventDTO.equals(resultEventDTO));
    }

    @Test
    public void updateEvent() {
        this.initializeMocksAndCustomDTOs();

        DetailedEventDto detailedEventDTO = this.customDetailedEventDto;
        DetailedEventDto resultEventDTO = this.eventService.updateEvent(1, 1, detailedEventDTO);

        Assertions.assertTrue(detailedEventDTO.equals(resultEventDTO));
    }

    @Test
    public void deleteEvent()
    {
        this.initializeMocksAndCustomDTOs();
        this.eventService.deleteEvent(1, 1);
    }

    public void getEventHeaderList()
    {

    }

    private void initializeMocksAndCustomDTOs() {
        this.initializeCustomDTOs();

        Mockito.when(this.eventRepositoryService.getEventAndHandleNotFound(Mockito.any(int.class)))
                .thenReturn(this.customEvent);
        Mockito.when(this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(Mockito.any(Event.class)))
                .thenReturn(this.customEvent);
        Mockito.when(this.eventRepositoryService.updateEventAndHandleNotFound(Mockito.any(int.class), Mockito.any(Event.class)))
                .thenReturn(this.customEvent);
        Mockito.when(this.delaitedEventDtoMapper.getDTOfromDetailedEvent(Mockito.any(Event.class)))
                .thenReturn(this.customDetailedEventDto);
        Mockito.when(this.delaitedEventDtoMapper.getDTOfromDetailedEvent(Mockito.any(Event.class)))
                .thenReturn(this.customDetailedEventDto);
        Mockito.when(this.delaitedEventDtoMapper.getEventFromDetailedEventDTO(Mockito.any(DetailedEventDto.class)))
                .thenReturn(this.customEvent);
        Mockito.when(this.viewEventDtoMapper.getEventFromViewEventDTO(Mockito.any(ViewEventDto.class)))
                .thenReturn(this.customEvent);
        Mockito.when(this.viewEventDtoMapper.getDTOfromViewEvent(Mockito.any(Event.class)))
                .thenReturn(this.customViewEventDto);
        Mockito.when(this.organizerService.getByID(Mockito.any(int.class)))
                .thenReturn(this.customOrganizer);
        Mockito.when(this.informationService.getByID(Mockito.any(int.class)))
                .thenReturn(this.customInformation);
    }
    private void initializeCustomDTOs() {
        this.initializeCustomEvent();
        this.initializeCustomInformation();
        this.initializeCustomOrganizer();
        this.initializeViewEventDTO();
        this.initializeDetailedEventDTO();
    }
    private void initializeCustomEvent() {
        this.customEvent = Event.builder()
                .id(1)
                .name("My Event")
                .eventLocation(new Location())
                .eventAds(new AdsOption())
                .build();
    }
    private void initializeCustomInformation() {
        this.customInformation=Information.builder()
                .id(1)
                .build();
    }
    private void initializeCustomOrganizer() {
        this.customOrganizer=Organizer.builder()
                .id(1)
                .information(customInformation)
                .build();
    }
    private void initializeViewEventDTO(){
        this.customViewEventDto = ViewEventDto.builder()
                .id(1)
                .name("My Event").build();
        this.customViewEventDto.setEventLocation(new Location());
    }
    private void initializeDetailedEventDTO() {
        this.customDetailedEventDto = DetailedEventDto.builder()
                .id(1)
                .name("My Event")
                .eventLocation(new Location())
                .eventAds(new AdsOptionDto()).build();
    }
}
