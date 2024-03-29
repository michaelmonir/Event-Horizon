package com.EventHorizon.EventHorizon.Service.EventServiceTests;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.Entities.EventEntities.*;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedDraftedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedEventDtoMapperFactory;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedEventDtoMapperInterface;
import com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos.DetailedLaunchedEventDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.ViewEventDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.*;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventServices.EventService;
import com.EventHorizon.EventHorizon.Services.EventServices.UserEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;

    @Mock
    private UserRepositoryService userRepositoryService;
    @Mock
    private UserEventService userEventService;
    @Mock
    private ViewEventDtoMapper viewEventDtoMapper;
    @Mock
    private DetailedLaunchedEventDtoMapper detailedLaunchedEventDtoMapper;
    @Mock
    private DetailedDraftedEventDtoMapper detailedDraftedEventDtoMapper;
    @Mock
    private DashboardRepositoryService dashboardRepositoryService;
    @Mock
    private DetailedLaunchedEventDto detailedLaunchedEventDto;
    @Mock
    private DetailedEventDtoMapperInterface detailedEventDtoMapperInterface;


//    @Test
//    void testGetEventForUser() {
//        when(launchedEventRepositoryService.getByIdAndHandleNotFound(anyInt())).thenReturn(new LaunchedEvent());
//        when(viewEventDtoMapper.getDTOfromViewEvent(any())).thenReturn(new ViewEventDto());
//        ViewEventDto result = eventService.getEventForUser(1);
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(ViewEventDto.class, result.getClass());
//    }

    // this function is just updated temporarily until the view event is fixed
    @Test
    void testGetEventForUser() {
        when(launchedEventRepositoryService.getByIdAndHandleNotFound(anyInt())).thenReturn(new LaunchedEvent());
        when(draftedEventRepositoryService.getByIdAndHandleNotFound(anyInt())).thenReturn(new DraftedEvent());

        when(detailedLaunchedEventDtoMapper.getDTOfromDetailedEvent(any()))
                .thenReturn(new DetailedLaunchedEventDto());
        when(detailedDraftedEventDtoMapper.getDTOfromDetailedEvent(any()))
                .thenReturn(new DetailedDraftedEventDto());

        DetailedEventDto result = eventService.getEventForUser(1);
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetEventForOrganizer() {

        when(eventRepositoryServiceInterface.getByIdAndEventType(anyInt(), any())).thenReturn(new DraftedEvent());

        when(detailedEventDtoMapperInterface.getDTOfromDetailedEvent(any())).thenReturn(new DetailedDraftedEventDto());
        DetailedEventDto result = eventService.getEventForOrganizer(1, 1, EventType.DRAFTEDEVENT);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(DetailedDraftedEventDto.class, result.getClass());
    }

    @Test
    void testGetEventHeadersList() {
        when(dashboardRepositoryService.getPage(anyInt(), anyInt())).thenReturn(new ArrayList<>());
        List<EventHeaderDto> result = eventService.getEventHeadersList(1, 10);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(ArrayList.class, result.getClass());
    }

    @Test
    void testCreateEvent() {
        Organizer organizer = new Organizer();
        DetailedLaunchedEventDto eventDTO = new DetailedLaunchedEventDto();

        when(userRepositoryService.getById(anyInt())).thenReturn(new Organizer());

        when(detailedDraftedEventDtoMapper.getEventFromDetailedEventDTO(any())).thenReturn(new DraftedEvent());
        when(detailedEventDtoMapperInterface.getDTOfromDetailedEvent(any())).thenReturn(new DetailedLaunchedEventDto());

        DetailedEventDto result = eventService.createEvent(1, eventDTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(DetailedLaunchedEventDto.class, result.getClass());
    }

    @Test
    void testUpdateEvent() {
        Organizer organizer = new Organizer();
        DetailedEventDto eventDTO = new DetailedDraftedEventDto();

        when(userRepositoryService.getById(anyInt())).thenReturn(new Organizer());

        when(eventRepositoryServiceInterface.getByIdAndEventType(anyInt(), any())).thenReturn(new LaunchedEvent());
        when(eventRepositoryServiceInterface.update(any())).thenReturn(new LaunchedEvent());

        when(detailedEventDtoMapperInterface.getDTOfromDetailedEvent(any())).thenReturn(detailedLaunchedEventDto);
        when(detailedEventDtoMapperInterface.getEventFromDetailedEventDTO(any())).thenReturn(new LaunchedEvent());

        DetailedEventDto result = eventService.updateEvent(1, eventDTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(DetailedLaunchedEventDto.class, result.getClass());
    }

    @Test
    void testDeleteEvent() {
        Organizer organizer = new Organizer();

        when(userRepositoryService.getById(anyInt())).thenReturn(new Organizer());
        when(eventRepositoryServiceInterface.getByIdAndEventType(anyInt(), any())).thenReturn(new LaunchedEvent());
        assertDoesNotThrow(() -> eventService.deleteEvent(1, 1, EventType.DRAFTEDEVENT));
    }
}