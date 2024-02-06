package com.EventHorizon.EventHorizon.Service.Event;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
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
    @Mock
    private GetUserRepositoryService getUserRepositoryService;

    @Test
    public void organizerOfEvent() {
        Event eventt = eventCreator.getLaunchedEvent();

        when(eventRepositoryServiceInterface.getByIdAndHandleNotFound(Mockito.any(int.class))).thenReturn(eventt);
        when(getUserRepositoryService.getOrganizerById(Mockito.any(int.class))).thenReturn(eventt.getEventOrganizer());
        Assertions.assertDoesNotThrow(() ->
            userEventService.checkOrganizerOfEvent(eventt.getEventOrganizer().getId(), eventt)
        );
    }

    @Test
    public void notOrganizerOfEvent() {
        Event event = eventCreator.getLaunchedEvent();

        when(eventRepositoryServiceInterface.getByIdAndHandleNotFound(Mockito.any(int.class))).thenReturn(event);

        Assertions.assertThrows(NotOrganizerOfThisEventException.class,() ->
            userEventService.checkOrganizerOfEvent(10000000, event) //////////////////////////////////
        );
    }
}
