package com.EventHorizon.EventHorizon.RepositoryServices.Event;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.DraftedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.SuperEventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@SpringBootTest
class EventRepositoryServiceFactoryTest {
    @InjectMocks
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;

    @Test
    void launchedEventRepositoryService() throws Exception {
        Method method = this.getMethodAndAdjustPublic();
        SuperEventRepositoryService result
                = (SuperEventRepositoryService) method.invoke(eventRepositoryServiceFactory, EventType.LAUNCHEDEVENT);
        Assertions.assertEquals(result.getClass(), LaunchedEventRepositoryService.class);
    }

    @Test
    public void draftedRepositoryService() throws Exception {
        Method method = this.getMethodAndAdjustPublic();
        SuperEventRepositoryService result
                = (SuperEventRepositoryService) method.invoke(eventRepositoryServiceFactory, EventType.DRAFTEDEVENT);
        Assertions.assertEquals(result.getClass(), DraftedEventRepositoryService.class);
    }

    private Method getMethodAndAdjustPublic() throws NoSuchMethodException {
        Method method = EventRepositoryServiceFactory.class.getDeclaredMethod("getByEventType", EventType.class);
        method.setAccessible(true);
        return method;
    }
}