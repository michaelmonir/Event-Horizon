package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventRepositoryServiceInterfaceTest {

    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    private EventCustomCreator eventCustomCreator;

    @Test
    public void testGetByIdLaunchedEvent() {
        LaunchedEvent launchedEvent = (LaunchedEvent) eventCustomCreator.getandSaveEvent(EventType.LAUNCHEDEVENT);

        LaunchedEvent launchedEventFromDb = (LaunchedEvent) eventRepositoryServiceInterface.getByIdAndHandleNotFound(launchedEvent.getId());
        Assertions.assertEquals(launchedEventFromDb.getEventType(), EventType.LAUNCHEDEVENT);
        Assertions.assertEquals(launchedEventFromDb, launchedEvent);
    }

    @Test
    public void testGetByIdDraftedEvent() {
        DraftedEvent draftedEvent = (DraftedEvent) eventCustomCreator.getandSaveEvent(EventType.DRAFTEDEVENT);

        DraftedEvent draftedEventFromDb = (DraftedEvent) eventRepositoryServiceInterface.getByIdAndHandleNotFound(draftedEvent.getId());
        Assertions.assertEquals(draftedEventFromDb.getEventType(), EventType.DRAFTEDEVENT);
        Assertions.assertEquals(draftedEventFromDb, draftedEvent);
    }
}
