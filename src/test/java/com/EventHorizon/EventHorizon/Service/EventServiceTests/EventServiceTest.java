package com.EventHorizon.EventHorizon.Service.EventServiceTests;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.*;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.EventCreationUpdationDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.*;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventServices.EventService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.OrganizerInformationRepositoryService;
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
    private InformationRepositoryService informationService;
    @Mock
    private UserEventService userEventService;
    @Mock
    private OrganizerInformationRepositoryService organizerInformationService;
    @Mock
    private DashboardRepositoryService dashboardRepositoryService;
    @Mock
    private EventCreationUpdationDtoMapper eventCreationUpdationDtoMapper;
    @Mock
    private EventViewDtoMapper eventViewDtoMapper;

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

        when(informationService.getByID(anyInt())).thenReturn(new Information());
        when(organizerInformationService.getUserByInformation(any())).thenReturn(organizer);

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

        when(informationService.getByID(anyInt())).thenReturn(new Information());
        when(organizerInformationService.getUserByInformation(any())).thenReturn(organizer);

        when(eventRepositoryServiceInterface.getById(anyInt())).thenReturn(new LaunchedEvent());
        when(eventRepositoryServiceInterface.update(any())).thenReturn(new LaunchedEvent());

        when(eventViewDtoMapper.getDetailedDtoFromEvent(any())).thenReturn(new EventViewDto());

        EventViewDto result = eventService.updateEvent(1, eventDTO);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(EventViewDto.class, result.getClass());
    }

    @Test
    void testDeleteEvent() {
        Organizer organizer = new Organizer();

        when(informationService.getByID(anyInt())).thenReturn(new Information());
        when(organizerInformationService.getUserByInformation(any())).thenReturn(organizer);
        when(eventRepositoryServiceInterface.getByIdAndEventType(anyInt(), any())).thenReturn(new LaunchedEvent());
        assertDoesNotThrow(() -> eventService.deleteEvent(1, 1, EventType.DRAFTEDEVENT));
    }

    @Test
    void testGetOrganizerFromInformationId() {
        when(informationService.getByID(anyInt())).thenReturn(new Information());
        when(organizerInformationService.getUserByInformation(any())).thenReturn(new Organizer());
        Organizer result = eventService.getOrganizerFromInformationId(1);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(Organizer.class, result.getClass());
    }
}