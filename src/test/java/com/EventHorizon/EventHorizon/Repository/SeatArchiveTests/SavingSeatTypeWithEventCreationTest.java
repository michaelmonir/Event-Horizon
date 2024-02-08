package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatType.SeatTypeCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidEventDataException;
import com.EventHorizon.EventHorizon.Repository.Event.DraftedEventRepository;
import com.EventHorizon.EventHorizon.Repository.Event.EventRepository;
import com.EventHorizon.EventHorizon.Repository.Event.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.DraftedEventRepositoryServiceImpl;
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
    DraftedEventRepositoryServiceImpl draftedEventRepositoryServiceImpl;
    @Autowired
    EventCustomCreator eventCustomCreator;
    @Autowired
    SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    SeatTypeRepository seatTypeRepository; // used only for finding by id not for creation
    @Autowired
    LaunchedEventRepository launchedEventRepository;
    @Autowired
    DraftedEventRepository draftedEventRepository;

    @Test
    public void creatingSeatTypeUsingRepositoryService() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        DraftedEvent event = this.getEventAndGiveOneSeatType(seatType);;

        Assertions.assertDoesNotThrow(() -> {
            draftedEventRepositoryServiceImpl.saveWhenCreating(event);
        });
    }

    @Test
    public void changingEventSeatTypesFromRepositoryDoesNotDeleteOldSeatTypes(){
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        DraftedEvent event = this.getEventAndGiveOneSeatType(seatType);

        draftedEventRepositoryServiceImpl.saveWhenCreating(event);

        event.setSeatTypes(new ArrayList<>());
        draftedEventRepository.save(event);

        Assertions.assertEquals(0, event.getSeatTypes().size());
        Assertions.assertNotEquals(0, this.seatTypeRepository.findAllByEventId(event.getId()).size());
    }

    @Test
    public void creatingSeatTypeUsingRepository() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        DraftedEvent event = this.getEventAndGiveOneSeatType(seatType);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepository.save(event);
        });
    }

    @Test
    public void creatingEventWithoutMakingName() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setName(null);
        DraftedEvent event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(InvalidEventDataException.class, () -> {
            draftedEventRepositoryServiceImpl.saveWhenCreating(event);
        });
    }

    @Test
    public void creatingEventWithMakingPriceNegative() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setPrice(-1);
        DraftedEvent event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(InvalidEventDataException.class, () -> {
            draftedEventRepositoryServiceImpl.saveWhenCreating(event);
        });
    }

    @Test
    public void creatingEventWithMakingNumberOfSeatsNegative() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setNumberOfSeats(-1);
        DraftedEvent event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(InvalidEventDataException.class, () -> {
            draftedEventRepositoryServiceImpl.saveWhenCreating(event);
        });
    }

    private DraftedEvent getEventAndGiveOneSeatType(SeatType seatType) {
        List <SeatType> seatTypes = new ArrayList<>();
        seatTypes.add(seatType);

        DraftedEvent event = (DraftedEvent) this.eventCustomCreator.getEvent(EventType.DRAFTEDEVENT);
        event.setSeatTypes(seatTypes);
        return event;
    }
}
