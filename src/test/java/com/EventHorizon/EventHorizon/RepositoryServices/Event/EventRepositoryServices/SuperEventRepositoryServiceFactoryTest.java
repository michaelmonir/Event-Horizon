package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.DraftedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.EventRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.LaunchedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.SuperEventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@SpringBootTest
class SuperEventRepositoryServiceFactoryTest {
    @Autowired
    private EventRepositoryServiceFactory eventRepositoryServiceFactory;

    @Test
    void launchedEventRepositoryService() throws Exception {
        Method method = this.getMethodAndAdjustPublic();
        SuperEventRepositoryService result
                = (SuperEventRepositoryService) method.invoke(eventRepositoryServiceFactory, EventType.LAUNCHEDEVENT);
        Assertions.assertTrue(LaunchedEventRepositoryServiceImpl.class.isAssignableFrom(result.getClass()));
    }

    @Test
    public void draftedRepositoryService() throws Exception {
        Method method = this.getMethodAndAdjustPublic();
        SuperEventRepositoryService result
                = (SuperEventRepositoryService) method.invoke(eventRepositoryServiceFactory, EventType.DRAFTEDEVENT);
        Assertions.assertTrue(DraftedEventRepositoryServiceImpl.class.isAssignableFrom(result.getClass()));
    }

    private Method getMethodAndAdjustPublic() throws NoSuchMethodException {
        Method method = EventRepositoryServiceFactory.class.getDeclaredMethod("getByEventType", EventType.class);
        method.setAccessible(true);
        return method;
    }
}