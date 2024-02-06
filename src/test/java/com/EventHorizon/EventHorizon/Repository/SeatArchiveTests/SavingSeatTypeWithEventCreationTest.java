package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatType.SeatTypeCustomCreator;
import com.EventHorizon.EventHorizon.Repository.Event.DraftedEventRepository;
import com.EventHorizon.EventHorizon.Repository.Event.EventRepository;
import com.EventHorizon.EventHorizon.Repository.Event.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SeatTypeRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.DraftedEventRepositoryService;
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
    DraftedEventRepositoryService draftedEventRepositoryService;
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
        Event event = this.getEventAndGiveOneSeatType(seatType);;

        Assertions.assertDoesNotThrow(() -> {
            draftedEventRepositoryService.saveWhenCreating(event);
        });
    }

    // this is because it doesn't use the repository service which uses the eventSeatReposirotyService
    // which deletes the old seatTypes
    @Test
    public void changingEventSeatTypesFromRepositoryDoesNotDeleteOldSeatTypes(){
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);

        draftedEventRepositoryService.saveWhenCreating(event);

        event.setSeatTypes(new ArrayList<>());
        if (event.getEventType() == EventType.LAUNCHEDEVENT)
            launchedEventRepository.save((LaunchedEvent) event);
        else if (event.getEventType() == EventType.DRAFTEDEVENT)
            draftedEventRepository.save((DraftedEvent) event);

        Assertions.assertEquals(0, event.getSeatTypes().size());
        Assertions.assertNotEquals(0, this.seatTypeRepository.findAllByEventId(event.getId()).size());
    }

    // use the repository service instead
//    @Test
//    public void updatingEventSeatTypesDeletesOldTypes(){
//        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
//        Event event = this.getEventAndGiveOneSeatType(seatType);
//
//        eventRepositoryService.saveWhenCreating(event);
//
//        event.setSeatTypes(new ArrayList<>());
//        eventRepositoryService.update(event);
//
//        Assertions.assertEquals(0, event.getSeatTypes().size());
//        Assertions.assertEquals(0, this.seatTypeRepository.findAllByEventId(event.getId()).size());
//    }

    @Test
    public void creatingSeatTypeUsingRepository() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);
        // as then the seatTypes would not have their event initialized
        // which normally takes place in the RepositoryService not the repository
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            eventRepository.save(event);
        });
    }

    @Test
    public void creatingEventWithoutMakingName() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setName(null);
        Event event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            draftedEventRepositoryService.saveWhenCreating(event);
        });
    }

    @Test
    public void creatingEventWithMakingPriceNegative() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setPrice(-1);
        Event event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            draftedEventRepositoryService.saveWhenCreating(event);
        });
    }

    @Test
    public void creatingEventWithMakingNumberOfSeatsNegative() {
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        seatType.setNumberOfSeats(-1);
        Event event = this.getEventAndGiveOneSeatType(seatType);

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            draftedEventRepositoryService.saveWhenCreating(event);
        });
    }

    private Event getEventAndGiveOneSeatType(SeatType seatType) {
        List <SeatType> seatTypes = new ArrayList<>();
        seatTypes.add(seatType);

        Event event = this.eventCustomCreator.getDraftedEvent();
        event.setSeatTypes(seatTypes);
        return event;
    }
}
