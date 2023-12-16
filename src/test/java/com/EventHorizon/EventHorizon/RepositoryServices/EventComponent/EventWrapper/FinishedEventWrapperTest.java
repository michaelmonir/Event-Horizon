package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventWrapper;

import com.EventHorizon.EventHorizon.Entities.EventEntities.EventWrapper.FinishedEventWrapper;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FinishedEventWrapperTest {

    @Test
    void testFinishedEventWrapper() {
        LaunchedEvent finishedEvent = new LaunchedEvent();
        finishedEvent.setEventDate(new Date(System.currentTimeMillis()));
        FinishedEventWrapper finishedEventWrapper = new FinishedEventWrapper(finishedEvent);
        assertEquals(finishedEvent, finishedEventWrapper.getLaunchedEvent());
    }
    @Test
    void testFinishedEventWrapperNotThrowError() {
        LaunchedEvent finishedEvent = new LaunchedEvent();
        finishedEvent.setEventDate(new Date(System.currentTimeMillis()-100));
        Assertions.assertDoesNotThrow(()->{
            FinishedEventWrapper finishedEventWrapper = new FinishedEventWrapper(finishedEvent);
            assertEquals(finishedEvent, finishedEventWrapper.getLaunchedEvent());
        });
    }
    @Test
    void testFinishedEventWrapperThrowError() {
        LaunchedEvent finishedEvent = new LaunchedEvent();
        finishedEvent.setEventDate(new Date(System.currentTimeMillis()+100000));
        Assertions.assertThrows(InvalidateException.class,()->{
            FinishedEventWrapper finishedEventWrapper = new FinishedEventWrapper(finishedEvent);
        });
    }

}