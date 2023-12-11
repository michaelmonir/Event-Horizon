package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidateException;
import org.junit.jupiter.api.Assertions;
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
        futureEvent.setEventDate(new Date(System.currentTimeMillis()));
        FinishedEventWrapper finishedEventWrapper = new FinishedEventWrapper(futureEvent);
        assertEquals(futureEvent, finishedEventWrapper.getLaunchedEvent());
    }
    @Test
    void testFutureEventWrapperNotThrowError() {
        LaunchedEvent futureEvent = new LaunchedEvent();
        futureEvent.setEventDate(new Date(System.currentTimeMillis()+1000));
        Assertions.assertDoesNotThrow(()->{
            FutureEventWrapper futureEventWrapper = new FutureEventWrapper(futureEvent);
            assertEquals(futureEvent, futureEventWrapper.getLaunchedEvent());
        });
    }
    @Test
    void testFutureEventWrapperThrowError() {
        LaunchedEvent futureEvent = new LaunchedEvent();
        futureEvent.setEventDate(new Date(System.currentTimeMillis()-100000));
        Assertions.assertThrows(InvalidateException.class,()->{
            FutureEventWrapper futureEventWrapper = new FutureEventWrapper(futureEvent);
        });
    }

}