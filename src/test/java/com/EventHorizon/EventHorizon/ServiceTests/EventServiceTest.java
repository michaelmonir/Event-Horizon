package com.EventHorizon.EventHorizon.ServiceTests;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.Entities.EventEntities.*;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.*;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.*;
import com.EventHorizon.EventHorizon.Services.EventService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.OrganizerInformationRepositoryService;
import com.EventHorizon.EventHorizon.Services.UserEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventServiceTest {
    @InjectMocks
    private EventService eventService;

    @Mock
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Mock
    private DraftedEventRepositoryService draftedEventRepositoryService;

    @Mock
    private DetailedEventDtoMapperFactory detailedEventDtoMapperFactory;

    @Mock
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;

    @Mock
    private UserEventService userEventService;

    @Mock
    private InformationRepositoryService informationService;

    @Mock
    private ViewEventDtoMapper viewEventDtoMapper;
    @Mock
    private DetailedLaunchedEventDtoMapper detailedLaunchedEventDtoMapper;
    @Mock
    private DetailedDraftedEventDtoMapper detailedDraftedEventDtoMapper;
    @Mock
    private OrganizerInformationRepositoryService organizerInformationService;
    @Mock
    private DashboardRepositoryService dashboardRepositoryService;


    @Test
    void testGetEventForUser() {
        when(launchedEventRepositoryService.getEventAndHandleNotFound(anyInt())).thenReturn(new LaunchedEvent());
        when(viewEventDtoMapper.getDTOfromViewEvent(any())).thenReturn(new ViewEventDto());
        ViewEventDto result = eventService.getEventForUser(1);
        assertNotNull(result);
        assertEquals(ViewEventDto.class, result.getClass());
    }

    @Test
    void testGetEventForOrganizer() {

        when(detailedEventDtoMapperFactory.getEventDtoMapperByEventType(any())).thenReturn(detailedDraftedEventDtoMapper);
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any())).thenReturn(draftedEventRepositoryService);
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any()).getEventAndHandleNotFound(anyInt())).thenReturn(new DraftedEvent());
        when(detailedDraftedEventDtoMapper.getDTOfromDetailedEvent(any())).thenReturn(new DetailedDraftedEventDto());
        DetailedEventDto result = eventService.getEventForOrganizer(1, 1, EventType.DRAFTEDEVENT);
        assertNotNull(result);
        assertEquals(DetailedDraftedEventDto.class, result.getClass());
    }

    @Test
    void testGetEventHeadersList() {
        when(dashboardRepositoryService.getPage(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        List<EventHeaderDto> result = eventService.getEventHeadersList(1, 10);
        assertNotNull(result);
        assertEquals(ArrayList.class, result.getClass());
    }

    @Test
    void testCreateEvent() {
        Organizer organizer = new Organizer();
        DetailedLaunchedEventDto eventDTO = new DetailedLaunchedEventDto();
        when(detailedEventDtoMapperFactory.getEventDtoMapperByEventType(any())).thenReturn(detailedLaunchedEventDtoMapper);
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any())).thenReturn(launchedEventRepositoryService);
        when(informationService.getByID(anyInt())).thenReturn(new Information());
        when(organizerInformationService.getUserByInformation(any())).thenReturn(organizer);
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any()).setEventOrganizer(any(), any())).thenReturn(new LaunchedEvent());
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any()).saveEventWhenCreatingAndHandleAlreadyExisting(any())).thenReturn(new LaunchedEvent());
        when(detailedEventDtoMapperFactory.getEventDtoMapperByEventType(any()).getDTOfromDetailedEvent(any())).thenReturn(new DetailedLaunchedEventDto());
        DetailedEventDto result = eventService.createEvent(1, eventDTO);
        assertNotNull(result);
        assertEquals(DetailedLaunchedEventDto.class, result.getClass());
    }

    @Test
    void testUpdateEvent() {
        Organizer organizer = new Organizer();
        DetailedEventDto eventDTO = new DetailedDraftedEventDto();
        when(detailedEventDtoMapperFactory.getEventDtoMapperByEventType(any())).thenReturn(detailedDraftedEventDtoMapper);
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any())).thenReturn(draftedEventRepositoryService);
        when(informationService.getByID(anyInt())).thenReturn(new Information());
        when(organizerInformationService.getUserByInformation(any())).thenReturn(organizer);
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any()).getEventAndHandleNotFound(anyInt())).thenReturn(new DraftedEvent());
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any()).updateEventAndHandleNotFound(any())).thenReturn(new DraftedEvent());
        when(detailedEventDtoMapperFactory.getEventDtoMapperByEventType(any()).getDTOfromDetailedEvent(any())).thenReturn(new DetailedDraftedEventDto());
        DetailedEventDto result = eventService.updateEvent(1, eventDTO);
        assertNotNull(result);
        assertEquals(DetailedDraftedEventDto.class, result.getClass());
    }

    @Test
    void testDeleteEvent() {
        Organizer organizer = new Organizer();
        DetailedLaunchedEventDto eventDTO = new DetailedLaunchedEventDto();
        when(detailedEventDtoMapperFactory.getEventDtoMapperByEventType(any())).thenReturn(new DetailedLaunchedEventDtoMapper());
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any())).thenReturn(launchedEventRepositoryService);
        when(informationService.getByID(anyInt())).thenReturn(new Information());
        when(organizerInformationService.getUserByInformation(any())).thenReturn(organizer);
        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any()).getEventAndHandleNotFound(anyInt())).thenReturn(new LaunchedEvent());
        assertDoesNotThrow(() -> eventService.deleteEvent(1, eventDTO));
    }

    @Test
    void testGetOrganizerFromInformationId() {
        when(informationService.getByID(anyInt())).thenReturn(new Information());
        when(organizerInformationService.getUserByInformation(any())).thenReturn(new Organizer());
        Organizer result = eventService.getOrganizerFromInformationId(1);
        assertNotNull(result);
        assertEquals(Organizer.class, result.getClass());
    }
}
