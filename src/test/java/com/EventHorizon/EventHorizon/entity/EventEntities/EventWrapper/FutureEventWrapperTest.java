package com.EventHorizon.EventHorizon.entity.EventEntities.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions.NotFutureEventException;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FutureEventWrapperTest {
    @Test
    void testFutureEventWrapper() {
        LaunchedEvent futureEvent = LaunchedEvent.builder()
                .eventDate(DateFunctions.getCurrentDate())
                .build();
        FutureEventWrapper futureEventWrapper = new FutureEventWrapper(futureEvent);
        assertEquals(futureEvent, futureEventWrapper.getLaunchedEvent());
    }
    @Test
    void testFutureEventWrapperNotThrowError() {
        LaunchedEvent futureEvent = new LaunchedEvent();
        futureEvent.setEventDate(DateFunctions.getCurrentDate());
        Assertions.assertDoesNotThrow(()->{
            FutureEventWrapper futureEventWrapper = new FutureEventWrapper(futureEvent);
            assertEquals(futureEvent, futureEventWrapper.getLaunchedEvent());
        });
    }
    @Test
    void testFutureEventWrapperThrowError() {
        LaunchedEvent futureEvent = new LaunchedEvent();
        futureEvent.setEventDate(DateFunctions.getYesterDaysDate());
        Assertions.assertThrows(NotFutureEventException.class,()->{
            FutureEventWrapper futureEventWrapper = new FutureEventWrapper(futureEvent);
        });
    }

}