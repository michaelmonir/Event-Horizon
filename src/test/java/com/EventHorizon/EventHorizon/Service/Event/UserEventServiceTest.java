package com.EventHorizon.EventHorizon.Service.Event;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceFacade;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.Event.UserEventService;
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
    private EventRepositoryServiceFacade eventRepositoryServiceFacade;
    @Autowired
    private EventCustomCreator eventCreator;
    @Mock
    private UserRepositoryService userRepositoryService;
    @Mock
    private GetUserRepositoryService getUserRepositoryService;

    @Test
    public void organizerOfEvent() {
        Event eventt = eventCreator.getEvent(EventType.LAUNCHEDEVENT);

        when(eventRepositoryServiceFacade.getByIdAndHandleNotFound(Mockito.any(int.class))).thenReturn(eventt);
        when(getUserRepositoryService.getOrganizerById(Mockito.any(int.class))).thenReturn(eventt.getEventOrganizer());
        Assertions.assertDoesNotThrow(() ->
            userEventService.checkOrganizerOfEvent(eventt.getEventOrganizer().getId(), eventt)
        );
    }

    @Test
    public void notOrganizerOfEvent() {
        Event event = eventCreator.getEvent(EventType.LAUNCHEDEVENT);

        when(eventRepositoryServiceFacade.getByIdAndHandleNotFound(Mockito.any(int.class))).thenReturn(event);

        Assertions.assertThrows(NotOrganizerOfThisEventException.class,() ->
            userEventService.checkOrganizerOfEvent(10000000, event) //////////////////////////////////
        );
    }
}
