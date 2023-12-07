package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FinishedEventWrapperTest {

    @Test
    void testFinishedEventWrapper() {
        LaunchedEvent finishedEvent = new LaunchedEvent();
        finishedEvent.setEvent(new Event());
        finishedEvent.setEventDate(new Date(System.currentTimeMillis()));
        FinishedEventWrapper finishedEventWrapper = new FinishedEventWrapper(finishedEvent);
        assertEquals(finishedEvent, finishedEventWrapper.getLaunchedEvent());
    }

}