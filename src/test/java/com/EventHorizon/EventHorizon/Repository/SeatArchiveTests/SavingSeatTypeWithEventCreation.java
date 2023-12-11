package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.*;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SavingSeatTypeWithEventCreation
{
    @Autowired
    LaunchedEventRepositoryService eventRepositoryService;
    @Autowired
    EventCustomCreator eventCustomCreator;
    @Autowired
    SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    EventRepositry eventRepositry;

    @Test
    public void creatingSeatTypeUsingRepositoryService()
    {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);;

        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        });
    }

    @Test
    public void creatingSeatTypeUsingRepository()
    {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);
        // as then the seatTypes would not have their event initialized
        // which normally takes place in the RepositoryService not the repository
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepositry.save(event);
        });
    }

    @Test
    public void creatingEventWithoutMakingName()
    {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setName(null);
        Event event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        });
    }

    @Test
    public void creatingEventWithMakingPriceNegative()
    {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setPrice(-1);
        Event event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        });
    }

    private Event getEventAndGiveOneSeatType(SeatType seatType)
    {
        List <SeatType> seatTypes = new ArrayList<>();
        seatTypes.add(seatType);

        Event event = this.eventCustomCreator.getLaunchedEvent();
        event.setSeatTypes(seatTypes);
        return event;
    }
}
