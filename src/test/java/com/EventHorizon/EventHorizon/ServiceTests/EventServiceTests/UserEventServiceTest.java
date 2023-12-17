package com.EventHorizon.EventHorizon.ServiceTests.EventServiceTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.EntityCustomCreators.EventCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.EntityCustomCreators.InformationCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.Services.EventServices.UserEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    private EventCustomCreator eventCreator;

    @Test
    public void organizerOfEvent() {
        Event eventt = eventCreator.getLaunchedEvent();

        when(eventRepositoryServiceInterface.getByIdAndEventType(Mockito.any(int.class), Mockito.any())).thenReturn(eventt);
        Assertions.assertDoesNotThrow(() -> {
            userEventService.checkAndHandleNotOrganizerOfEvent(eventt.getEventOrganizer(), eventt);
        });
    }

    @Test
    public void notOrganizerOfEvent() {
        Event event = eventCreator.getLaunchedEvent();

        when(eventRepositoryServiceInterface.getByIdAndEventType(Mockito.any(int.class), Mockito.any())).thenReturn(event);

        Assertions.assertThrows(NotOrganizerOfThisEventException.class,() -> {
            userEventService.checkAndHandleNotOrganizerOfEvent(Organizer.builder().id(10000).build(), event);
        });
    }
}
