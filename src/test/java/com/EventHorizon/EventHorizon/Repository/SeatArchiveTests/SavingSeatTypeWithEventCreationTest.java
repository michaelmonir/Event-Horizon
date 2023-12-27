package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.*;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.DraftedEventRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.LaunchedEventRepository;
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
            eventRepositoryService.saveWhenCreating(event);
        });
    }

    // this is because it doesn't use the repository service which uses the eventSeatReposirotyService
    // which deletes the old seatTypes
    @Test
    public void changingEventSeatTypesFromRepositoryDoesNotDeleteOldSeatTypes(){
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);

        eventRepositoryService.saveWhenCreating(event);

        event.setSeatTypes(new ArrayList<>());
        if (event.getEventType() == EventType.LAUNCHEDEVENT)
            launchedEventRepository.save((LaunchedEvent) event);
        else if (event.getEventType() == EventType.DRAFTEDEVENT)
            draftedEventRepository.save((DraftedEvent) event);

        Assertions.assertEquals(0, event.getSeatTypes().size());
        Assertions.assertNotEquals(0, this.seatTypeRepository.findAllByEventId(event.getId()).size());
    }

    // use the repository service instead
    @Test
    public void updatingEventSeatTypesDeletesOldTypes(){
        SeatType seatType = this.seatTypeCustomCreator.getSeatType();
        Event event = this.getEventAndGiveOneSeatType(seatType);

        eventRepositoryService.saveWhenCreating(event);

        event.setSeatTypes(new ArrayList<>());
        eventRepositoryService.update(event);

        Assertions.assertEquals(0, event.getSeatTypes().size());
        Assertions.assertEquals(0, this.seatTypeRepository.findAllByEventId(event.getId()).size());
    }

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
