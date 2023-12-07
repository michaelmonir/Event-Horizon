package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeCustomCreator;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.OrganizerSeatArchiveRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SponsorSeatArchiveTest {

    @Autowired
    OrganizerSeatArchiveRepository organizerSeatArchiveRepository;
    @Autowired
    SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    EventCustomCreator eventCustomCreator;
    @Autowired
    EventRepositoryService eventRepositoryService;

    @Test
    public void saveSuccessfully() {

        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, 1);

        Assertions.assertDoesNotThrow(()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
    }

    @Test
    public void saveWithoutSavingTheSeatType() {

        SeatType seatType = new SeatType("s", 1);
        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, 1);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
    }

    @Test
    public void saveNegativeAvailableNumberOfSeats() {

        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, -1);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
    }

    @Test
    public void saveTotalNumberOfSeatsLessThanAvailable() {

        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, 2);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
    }

    private SeatType getAndCreateCustomSeatTypeFromSavedEvent() {
        SeatType customSeatType = this.seatTypeCustomCreator.getSeatType();
        Event customEvent = this.eventCustomCreator.getEvent();
        List<SeatType> seatTypesList = new ArrayList<>();
        seatTypesList.add(customSeatType);

        customEvent.setSeatTypes(seatTypesList);
        customEvent = this.eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(customEvent);

        return customEvent.getSeatTypes().get(0);
    }
}
