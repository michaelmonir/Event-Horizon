//package com.EventHorizon.EventHorizon.ServiceTests;
//
//import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
//import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedDraftedEventDto;
//import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
//import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
//import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
//import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
//import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
//import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
//import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
//import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
//import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
//import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.DelaitedEventDtoMapper;
//import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.ViewEventDtoMapper;
//import com.EventHorizon.EventHorizon.Services.EventService;
//import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
//import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.OrganizerInformationRepositoryService;
//import com.EventHorizon.EventHorizon.Services.UserEventService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class EventServiceTest {
//    @Mock
//    private EventRepositoryService eventRepositoryService;
//    @Mock
//    private DelaitedEventDtoMapper delaitedEventDtoMapper;
//    @Mock
//    private ViewEventDtoMapper viewEventDtoMapper;
//    @Mock
//    private InformationRepositoryService informationService;
//    @InjectMocks
//    private EventService eventService;
//    @Mock
//    private OrganizerInformationRepositoryService organizerInformationService;
//    @Mock
//    private UserEventService userEventService;
//
//    private Event customEvent;
//    private ViewEventDto customViewEventDto;
//    private DetailedLaunchedEventDto customDetailedLaunchedEventDto;
//    private DetailedDraftedEventDto customDetailedDraftedEventDto;
//    private Organizer customOrganizer;
//    private Information customInformation;
//    private LaunchedEvent customLaunchedEvent;
//    private LaunchedEvent customDraftedEvent;
//
//
//    @Test
//    public void gettingEventForUser() {
//        this.initializeMocksAndCustomDTOs();
//
//        ViewEventDto viewEventDTO = this.eventService.getEventForUser(1);
//        ViewEventDto expectedViewEventDto = this.customViewEventDto;
//
//        Assertions.assertTrue(viewEventDTO.equals(expectedViewEventDto));
//    }
//
//    @Test
//    public void gettingOrganizerEvent() {
//        this.initializeMocksAndCustomDTOs();
//        this.eventService.getEventForUser(1);
//        DetailedLaunchedEventDto detailedLaunchedEventDTO = this.eventService.getEventForOrganizer(1, 1);
//        DetailedLaunchedEventDto expectedViewEventDTO = this.customDetailedLaunchedEventDto;
//
//        Assertions.assertTrue(expectedViewEventDTO.equals(detailedLaunchedEventDTO));
//    }
//
//    @Test
//    public void createEvent() {
//        this.initializeMocksAndCustomDTOs();
//
//        DetailedLaunchedEventDto detailedLaunchedEventDTO = this.customDetailedLaunchedEventDto;
//        DetailedLaunchedEventDto resultEventDTO = this.eventService.createEvent(1, detailedLaunchedEventDTO);
//
//        Assertions.assertTrue(detailedLaunchedEventDTO.equals(resultEventDTO));
//    }
//
//    @Test
//    public void updateEvent() {
//        this.initializeMocksAndCustomDTOs();
//
//        DetailedLaunchedEventDto detailedLaunchedEventDTO = this.customDetailedLaunchedEventDto;
//        DetailedLaunchedEventDto resultEventDTO = this.eventService.updateEvent(1, detailedLaunchedEventDTO);
//
//        Assertions.assertTrue(detailedLaunchedEventDTO.equals(resultEventDTO));
//    }
//
//    @Test
//    public void deleteEvent() {
//        this.initializeMocksAndCustomDTOs();
//        this.eventService.deleteEvent(1, 1);
//    }
//
//    private void initializeMocksAndCustomDTOs() {
//        this.initializeCustomDTOs();
//
//        this.initializeEventRepositoryServiceMockito();
//        this.initializeDetailedEventDtoMapper();
//        this.initializeEventDtoMapper();
//        this.initializerInformationService();
//        this.initializeOrganizerInformationService();
//    }
//
//    private void initializeEventRepositoryServiceMockito() {
//        Mockito.when(this.eventRepositoryService.getEventAndHandleNotFound(Mockito.any(int.class)))
//                .thenReturn(this.customEvent);
//        Mockito.when(this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(Mockito.any(Event.class)))
//                .thenReturn(this.customEvent);
//        Mockito.when(this.eventRepositoryService.updateEventAndHandleNotFound(Mockito.any(Event.class)))
//                .thenReturn(this.customEvent);
//    }
//
//    private void initializeDetailedEventDtoMapper() {
//        Mockito.when(this.delaitedEventDtoMapper.getDTOfromDetailedEvent(Mockito.any(Event.class)))
//                .thenReturn(this.customDetailedLaunchedEventDto);
//        Mockito.when(this.delaitedEventDtoMapper.getDTOfromDetailedEvent(Mockito.any(Event.class)))
//                .thenReturn(this.customDetailedLaunchedEventDto);
//        Mockito.when(this.delaitedEventDtoMapper.getEventFromDetailedEventDTO(Mockito.any(DetailedLaunchedEventDto.class)))
//                .thenReturn(this.customEvent);
//    }
//
//    private void initializeEventDtoMapper() {
//        Mockito.when(this.viewEventDtoMapper.getEventFromViewEventDTO(Mockito.any(ViewEventDto.class)))
//                .thenReturn(this.customEvent);
//        Mockito.when(this.viewEventDtoMapper.getDTOfromViewEvent(Mockito.any(Event.class)))
//                .thenReturn(this.customViewEventDto);
//    }
//
//    private void initializerInformationService() {
//        Mockito.when(this.informationService.getByID(Mockito.any(int.class)))
//                .thenReturn(this.customInformation);
//    }
//
//    private void initializeOrganizerInformationService() {
//        Mockito.when(this.organizerInformationService.getUserByInformation(Mockito.any(Information.class)))
//                .thenReturn(this.customOrganizer);
//    }
//
//
//    private void initializeCustomDTOs() {
//        this.initializeCustomEvent();
//        this.initializeCustomInformation();
//        this.initializeCustomOrganizer();
//        this.initializeViewEventDTO();
//        this.initializeDetailedLaunchedEventDTO();
//        this.initializeDetailedDraftedEventDTO();
//    }
//
//    private void initializeCustomEvent() {
//        this.customEvent = Event.builder()
//                .id(1)
//                .name("My Event")
//                .eventLocation(new Location())
//                .eventAds(new AdsOption())
//                .build();
//    }
//
//    private void initializeCustomInformation() {
//        this.customInformation = Information.builder()
//                .id(1)
//                .build();
//    }
//
//    private void initializeCustomOrganizer() {
//        this.customOrganizer = Organizer.builder()
//                .id(1)
//                .information(customInformation)
//                .build();
//    }
//
//    private void initializeViewEventDTO() {
//        this.customViewEventDto = ViewEventDto.builder()
//                .id(1)
//                .name("My Event").build();
//        this.customViewEventDto.setEventLocation(new Location());
//    }
//
//    private void initializeDetailedLaunchedEventDTO() {
//        this.customDetailedLaunchedEventDto = DetailedLaunchedEventDto.builder()
//                .id(1)
//                .name("My Event")
//                .eventLocation(new Location())
//                .eventAds(new AdsOptionDto()).build();
//    }
//
//    private void initializeDetailedDraftedEventDTO() {
//        this.customDetailedDraftedEventDto = DetailedDraftedEventDto.builder()
//                .id(1)
//                .name("My Event")
//                .eventLocation(new Location())
//                .eventAds(new AdsOptionDto()).build();
//    }
//    public void initializeLaunchedEvent(){
//        this.customLaunchedEvent.builder().event(customEvent).build();
//    }
//    public void initializeDraftedEvent(){
//        this.customDraftedEvent.builder().event(customEvent).build();
//    }
//}
