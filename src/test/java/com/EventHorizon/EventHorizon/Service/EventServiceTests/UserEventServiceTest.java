package com.EventHorizon.EventHorizon.Service.EventServiceTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.EntityCustomCreators.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventServices.UserEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserEventServiceTest {
    @InjectMocks
    private UserEventService userEventService;
    @Mock
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    private EventCustomCreator eventCreator;
    @Mock
    private UserRepositoryService userRepositoryService;

    @Test
    public void organizerOfEvent() {
        Event eventt = eventCreator.getLaunchedEvent();

        when(eventRepositoryServiceInterface.getById(Mockito.any(int.class))).thenReturn(eventt);
        when(userRepositoryService.getOrganizerById(Mockito.any(int.class))).thenReturn(eventt.getEventOrganizer());
        Assertions.assertDoesNotThrow(() ->
            userEventService.getAndHandleNotOrganizerOfEvent(eventt.getEventOrganizer().getId(), eventt)
        );
    }

    @Test
    public void notOrganizerOfEvent() {
        Event event = eventCreator.getLaunchedEvent();

        when(eventRepositoryServiceInterface.getById(Mockito.any(int.class))).thenReturn(event);

        Assertions.assertThrows(NotOrganizerOfThisEventException.class,() ->
            userEventService.getAndHandleNotOrganizerOfEvent(10000000, event) //////////////////////////////////
        );
    }
}
