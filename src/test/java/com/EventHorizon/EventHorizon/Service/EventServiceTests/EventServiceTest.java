package com.EventHorizon.EventHorizon.Service.EventServiceTests;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.*;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.Event.EventCreationUpdationDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
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
    @Mock
    private GetUserRepositoryService getUserRepositoryService;

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

        when(eventRepositoryServiceInterface.getById(anyInt())).thenReturn(new LaunchedEvent());
        when(eventRepositoryServiceInterface.update(any())).thenReturn(new LaunchedEvent());

        when(eventViewDtoMapper.getDetailedDtoFromEvent(any())).thenReturn(new EventViewDto());

        EventViewDto result = eventService.updateEvent(1, eventDTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(EventViewDto.class, result.getClass());
    }

    @Test
    void testDeleteEvent() {
        when(getUserRepositoryService.getById(anyInt())).thenReturn(new Organizer());
        when(eventRepositoryServiceInterface.getById(anyInt())).thenReturn(new LaunchedEvent());
        assertDoesNotThrow(() -> eventService.deleteEvent(1, 1, EventType.DRAFTEDEVENT));
    }
}