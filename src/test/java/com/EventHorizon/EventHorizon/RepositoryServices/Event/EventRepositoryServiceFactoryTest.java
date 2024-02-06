package com.EventHorizon.EventHorizon.RepositoryServices.Event;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.SuperEventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
@SpringBootTest
class EventRepositoryServiceFactoryTest {
    @Mock
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Mock
    private DraftedEventRepositoryService draftedEventRepositoryService;
    @InjectMocks
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;

    @Test
    void launchedEventRepositoryService() {
        SuperEventRepositoryService result
                = eventRepositoryServiceFactory.getByEventType(EventType.LAUNCHEDEVENT);
        Assertions.assertEquals(result.getClass(), LaunchedEventRepositoryService.class);
    }

    @Test
    public void draftedRepositoryService() {
        SuperEventRepositoryService result
                = eventRepositoryServiceFactory.getByEventType(EventType.DRAFTEDEVENT);
        Assertions.assertEquals(result.getClass(), DraftedEventRepositoryService.class);
    }
}