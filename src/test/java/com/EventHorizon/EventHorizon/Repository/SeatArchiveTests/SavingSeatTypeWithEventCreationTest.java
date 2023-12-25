package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.*;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepositry;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SavingSeatTypeWithEventCreationTest
{
    @Autowired
    LaunchedEventRepositoryService eventRepositoryService;
    @Autowired
    EventCustomCreator eventCustomCreator;
    @Autowired
    SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    EventRepositry eventRepositry;
    @Autowired
    SeatTypeRepository seatTypeRepository; // used only for finding by id not for creation

    @Test
    public void creatingSeatTypeUsingRepositoryService() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);;

        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.saveWhenCreating(event);
        });
    }

    // this test means that changing the seatType doesn'tttttt mean that it will
    // automatically remove the old seat Types from data database
    @Test
    public void testChangingEventSeatTypesDoesNotDeleteOldTypes() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);

        eventRepositoryService.saveWhenCreating(event);

        event.setSeatTypes(new ArrayList<>());
        eventRepositoryService.update(event);

        Assertions.assertEquals(0, event.getSeatTypes().size());
        Assertions.assertNotEquals(0, this.seatTypeRepository.findAllByEventId(event.getId()).size());
    }

    @Test
    public void creatingSeatTypeUsingRepository() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);
        // as then the seatTypes would not have their event initialized
        // which normally takes place in the RepositoryService not the repository
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepositry.save(event);
        });
    }

    @Test
    public void creatingEventWithoutMakingName() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setName(null);
        Event event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepositoryService.saveWhenCreating(event);
        });
    }

    @Test
    public void creatingEventWithMakingPriceNegative() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setPrice(-1);
        Event event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepositoryService.saveWhenCreating(event);
        });
    }

    @Test
    public void creatingEventWithMakingNumberOfSeatsNegative() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setNumberOfSeats(-1);
        Event event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepositoryService.saveWhenCreating(event);
        });
    }

    private Event getEventAndGiveOneSeatType(SeatType seatType) {
        List <SeatType> seatTypes = new ArrayList<>();
        seatTypes.add(seatType);

        Event event = this.eventCustomCreator.getLaunchedEvent();
        event.setSeatTypes(seatTypes);
        return event;
    }
}
