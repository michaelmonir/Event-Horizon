package com.EventHorizon.EventHorizon.entity.EventEntities.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.Event.EventWrapper.FinishedEventWrapper;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventTypeExceptions.NotFinishedEventException;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FinishedEventWrapperTest {

    @Test
    void testFinishedEventWrapper() {
        LaunchedEvent finishedEvent = new LaunchedEvent();
        finishedEvent.setEventDate(DateFunctions.getYesterDaysDate());
        FinishedEventWrapper finishedEventWrapper = new FinishedEventWrapper(finishedEvent);
        assertEquals(finishedEvent, finishedEventWrapper.getLaunchedEvent());
    }
    @Test
    void testFinishedEventWrapperNotThrowError() {
        LaunchedEvent finishedEvent = new LaunchedEvent();
        finishedEvent.setEventDate(DateFunctions.getYesterDaysDate());
        Assertions.assertDoesNotThrow(()->{
            FinishedEventWrapper finishedEventWrapper = new FinishedEventWrapper(finishedEvent);
            assertEquals(finishedEvent, finishedEventWrapper.getLaunchedEvent());
        });
    }
    @Test
    void testFinishedEventWrapperThrowError() {
        LaunchedEvent finishedEvent = new LaunchedEvent();
        finishedEvent.setEventDate(DateFunctions.getCurrentDate());
        Assertions.assertThrows(NotFinishedEventException.class,()->{
            new FinishedEventWrapper(finishedEvent);
        });
    }

}