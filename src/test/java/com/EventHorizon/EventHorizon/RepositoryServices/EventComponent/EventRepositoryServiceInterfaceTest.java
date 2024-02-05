package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.LaunchedEventRepositoryService;
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
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;

    @Test
    public void testGetByIdLaunchedEvent() {
        LaunchedEvent launchedEvent = eventCustomCreator.getLaunchedEvent();
        launchedEventRepositoryService.saveWhenCreating(launchedEvent);
        LaunchedEvent launchedEventFromDb = (LaunchedEvent) eventRepositoryServiceInterface.getByIdAndHandleNotFound(launchedEvent.getId());
        Assertions.assertEquals(launchedEventFromDb.getEventType(), EventType.LAUNCHEDEVENT);
        Assertions.assertEquals(launchedEventFromDb, launchedEvent);
    }

    @Test
    public void testGetByIdDraftedEvent() {
        DraftedEvent draftedEvent = eventCustomCreator.getDraftedEvent();
        draftedEventRepositoryService.saveWhenCreating(draftedEvent);
        DraftedEvent draftedEventFromDb = (DraftedEvent) eventRepositoryServiceInterface.getByIdAndHandleNotFound(draftedEvent.getId());
        Assertions.assertEquals(draftedEventFromDb.getEventType(), EventType.DRAFTEDEVENT);
        Assertions.assertEquals(draftedEventFromDb, draftedEvent);
    }
}
