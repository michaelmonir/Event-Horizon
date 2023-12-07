package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FutureEventWrapperTest {
    @Test
    void testFutureEventWrapper() {
        LaunchedEvent futureEvent = new LaunchedEvent();
        futureEvent.setEvent(new Event());
        futureEvent.setEventDate(new Date(System.currentTimeMillis()));
        FinishedEventWrapper finishedEventWrapper = new FinishedEventWrapper(futureEvent);
        assertEquals(futureEvent, finishedEventWrapper.getLaunchedEvent());
    }

}