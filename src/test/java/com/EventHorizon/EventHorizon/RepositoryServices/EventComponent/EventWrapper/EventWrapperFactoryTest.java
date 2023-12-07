package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EventWrapperFactoryTest {

    @Autowired
    private EventWrapperFactory eventWrapperFactory;
    @Test
    void getEventWrapperForPastEvent() {
        LaunchedEvent pastEvent = new LaunchedEvent();
        pastEvent.setEvent(new Event());
        pastEvent.setEventDate(new Date(System.currentTimeMillis() - 100000));
        EventWrapper eventWrapper = eventWrapperFactory.getEventWrapper(pastEvent);
        assertTrue(eventWrapper instanceof FinishedEventWrapper);
    }

    @Test
    void getEventWrapperForFutureEvent() {
        LaunchedEvent futureEvent = new LaunchedEvent();
        futureEvent.setEvent(new Event());
        futureEvent.setEventDate(new Date(System.currentTimeMillis() + 100000));
        EventWrapper eventWrapper = eventWrapperFactory.getEventWrapper(futureEvent);
        assertTrue(eventWrapper instanceof FutureEventWrapper);
    }

    @Test
    void getEventWrapperForCurrentEvent() {
        LaunchedEvent futureEvent = new LaunchedEvent();
        futureEvent.setEvent(new Event());
        futureEvent.setEventDate(new Date(System.currentTimeMillis()));
        EventWrapper eventWrapper = eventWrapperFactory.getEventWrapper(futureEvent);
        assertTrue(eventWrapper instanceof FutureEventWrapper);
    }

}