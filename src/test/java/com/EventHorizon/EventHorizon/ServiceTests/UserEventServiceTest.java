package com.EventHorizon.EventHorizon.ServiceTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.EntityCustomCreators.InformationCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.Services.UserEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserEventServiceTest {
    @InjectMocks
    private UserEventService userEventService;
    @Autowired
    private InformationCustomCreator informationCustomCreator;

    @Mock
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;
    @Mock
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Test
    public void organizerOfEvent() {
        Information information = informationCustomCreator.getInformation(Role.ORGANIZER);
        Organizer organizer = Organizer.builder().information(information).build();
        LaunchedEvent event = LaunchedEvent.builder()
                .eventOrganizer(organizer)
                .id(1)
                .build();

        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any())).thenReturn(launchedEventRepositoryService);
        when(launchedEventRepositoryService.getEventAndHandleNotFound(1)).thenReturn(event);
        Assertions.assertDoesNotThrow(() -> {
            userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
        });
    }

    @Test
    public void notOrganizerOfEvent() {
        Information information = informationCustomCreator.getInformation(Role.ORGANIZER);
        Organizer organizer = Organizer.builder().information(information).build();
        LaunchedEvent event = LaunchedEvent.builder()
                .eventOrganizer(organizer)
                .id(1)
                .build();
        Information information2 = informationCustomCreator.getInformation(Role.ORGANIZER);
        Organizer organizer2 = Organizer.builder().information(information2).build();


        when(eventRepositoryServiceFactory.getEventRepositoryServiceByEventType(any())).thenReturn(launchedEventRepositoryService);
        when(launchedEventRepositoryService.getEventAndHandleNotFound(1)).thenReturn(event);

        Assertions.assertThrows(NotOrganizerOfThisEventException.class,() -> {
            userEventService.checkAndHandleNotOrganizerOfEvent(organizer2, event);
        });
    }
}
