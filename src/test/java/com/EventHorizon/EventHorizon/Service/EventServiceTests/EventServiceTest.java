package com.EventHorizon.EventHorizon.Service.EventServiceTests;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.Entities.EventEntities.*;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.EventCreationUpdationDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.EventViewDtoMapper;
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
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Mock
    private UserRepositoryService userRepositoryService;
    @Mock
    private UserEventService userEventService;
    @Mock
    private DashboardRepositoryService dashboardRepositoryService;
    @Mock
    private EventCreationUpdationDtoMapper eventCreationUpdationDtoMapper;
    @Mock
    private EventViewDtoMapper eventViewDtoMapper;

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

        when(eventViewDtoMapper.getDetailedEventFromEventDto(any()))
                .thenReturn(new EventViewDto());
        when(eventViewDtoMapper.getDetailedEventFromEventDto(any()))
                .thenReturn(new EventViewDto());

        EventViewDto result = eventService.getEventForUser(1);
        Assertions.assertNotNull(result);
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
        EventCreationUpdationDto eventDTO = new EventCreationUpdationDto();

        when(userRepositoryService.getById(anyInt())).thenReturn(new Organizer());

        when(eventCreationUpdationDtoMapper.getEventFromDtoForCreating(any())).thenReturn(new DraftedEvent());
        when(eventViewDtoMapper.getDetailedEventFromEventDto(any())).thenReturn(new EventViewDto());

        EventViewDto result = eventService.createEvent(1, eventDTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(EventViewDto.class, result.getClass());
    }

    @Test
    void testUpdateEvent() {
        Organizer organizer = new Organizer();
        EventCreationUpdationDto eventDTO = new EventCreationUpdationDto();

        when(userRepositoryService.getById(anyInt())).thenReturn(new Organizer());

        when(eventRepositoryServiceInterface.getById(anyInt())).thenReturn(new LaunchedEvent());
        when(eventRepositoryServiceInterface.update(any())).thenReturn(new LaunchedEvent());

        when(eventViewDtoMapper.getDetailedEventFromEventDto(any())).thenReturn(new EventViewDto());

        EventViewDto result = eventService.updateEvent(1, eventDTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(EventViewDto.class, result.getClass());
    }

    @Test
    void testDeleteEvent() {
        when(userRepositoryService.getById(anyInt())).thenReturn(new Organizer());
        when(eventRepositoryServiceInterface.getById(anyInt())).thenReturn(new LaunchedEvent());
        assertDoesNotThrow(() -> eventService.deleteEvent(1, 1, EventType.DRAFTEDEVENT));
    }
}