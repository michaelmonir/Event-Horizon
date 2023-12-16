package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.EventRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.SuperEventRepositoryService;
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
    void getEventRepositoryServiceByEventType_LaunchedEvent_ReturnsLaunchedEventRepositoryService() {
        EventType eventType = EventType.LAUNCHEDEVENT;

        SuperEventRepositoryService result = eventRepositoryServiceFactory.getByEventType(eventType);
        result.delete(0);
        verifyNoInteractions(draftedEventRepositoryService);
        verify(launchedEventRepositoryService).delete(0);

    }

    @Test
    void getEventRepositoryServiceByEventType_DraftedEvent_ReturnsDraftedEventRepositoryService() {
        EventType eventType = EventType.DRAFTEDEVENT;

        SuperEventRepositoryService result = eventRepositoryServiceFactory.getByEventType(eventType);
        result.delete(0);
        verifyNoInteractions(launchedEventRepositoryService); // Ensure the other service is not called
        verify(draftedEventRepositoryService).delete(0); // Add relevant method from DraftedEventRepositoryService

    }

}