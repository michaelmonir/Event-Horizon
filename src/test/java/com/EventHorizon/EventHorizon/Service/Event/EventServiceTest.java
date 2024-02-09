package com.EventHorizon.EventHorizon.Service.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.Event.*;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Mappers.Event.EventCreationUpdationDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Utility.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.DraftedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.EventRepositoryServiceFacadeImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.LaunchedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.Event.EventService;
import com.EventHorizon.EventHorizon.Services.Event.UserEventService;
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
    private LaunchedEventRepositoryServiceImpl launchedEventRepositoryServiceImpl;
    @Mock
    private DraftedEventRepositoryServiceImpl draftedEventRepositoryServiceImpl;
    @Mock
    private EventRepositoryServiceFacadeImpl eventRepositoryServiceFacadeImpl;
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
    @Mock
    private GetUserRepositoryService getUserRepositoryService;

//    @Test
//    void testGetEventHeadersList() {
//        when(dashboardRepositoryService.getPage(anyInt(), anyInt())).thenReturn(new ArrayList<>());
//        List<EventHeaderDto> result = eventService.getEventHeadersList(1, 10);
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(ArrayList.class, result.getClass());
//    }

    @Test
    void testCreateEvent() {
        Organizer organizer = new Organizer();
        EventCreationUpdationDto eventDTO = new EventCreationUpdationDto();

        when(getUserRepositoryService.getById(anyInt())).thenReturn(new Organizer());

        when(eventCreationUpdationDtoMapper.getEventFromDtoForCreating(any())).thenReturn(new DraftedEvent());
        when(eventViewDtoMapper.getDetailedDtoFromEvent(any())).thenReturn(new EventViewDto());

        EventViewDto result = eventService.createEvent(1, eventDTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(EventViewDto.class, result.getClass());
    }

    @Test
    void testUpdateEvent() {
        Organizer organizer = new Organizer();
        EventCreationUpdationDto eventDTO = new EventCreationUpdationDto();

        when(getUserRepositoryService.getById(anyInt())).thenReturn(new Organizer());

        when(eventRepositoryServiceFacadeImpl.getById(anyInt())).thenReturn(new LaunchedEvent());
        when(eventRepositoryServiceFacadeImpl.update(any())).thenReturn(new LaunchedEvent());

        when(eventViewDtoMapper.getDetailedDtoFromEvent(any())).thenReturn(new EventViewDto());

        EventViewDto result = eventService.updateEvent(1, eventDTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(EventViewDto.class, result.getClass());
    }

    @Test
    void testDeleteEvent() {
        when(getUserRepositoryService.getById(anyInt())).thenReturn(new Organizer());
        when(eventRepositoryServiceFacadeImpl.getById(anyInt())).thenReturn(new LaunchedEvent());
        assertDoesNotThrow(() -> eventService.deleteEvent(1, 1));
    }
}