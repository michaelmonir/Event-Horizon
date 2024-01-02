package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.EventCustomCreator;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventRepositoryServiceTest {

    @Autowired
    private EventRepositoryService eventRepositoryServiceTest;
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
        LaunchedEvent launchedEventFromDb = (LaunchedEvent) eventRepositoryServiceTest.getById(launchedEvent.getId());
        Assertions.assertEquals(launchedEventFromDb.getEventType(), EventType.LAUNCHEDEVENT);
        Assertions.assertEquals(launchedEventFromDb, launchedEvent);
    }

    @Test
    public void testGetByIdDraftedEvent() {
        DraftedEvent draftedEvent = eventCustomCreator.getDraftedEvent();
        draftedEventRepositoryService.saveWhenCreating(draftedEvent);
        DraftedEvent draftedEventFromDb = (DraftedEvent) eventRepositoryServiceTest.getById(draftedEvent.getId());
        Assertions.assertEquals(draftedEventFromDb.getEventType(), EventType.DRAFTEDEVENT);
        Assertions.assertEquals(draftedEventFromDb, draftedEvent);
    }
}
