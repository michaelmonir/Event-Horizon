package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.OrganizerSeatArchiveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.swing.text.html.Option;
import java.util.Optional;


@SpringBootTest
public class OrganizerSeatArchiveRepositoryTest {

    @Autowired
    OrganizerSeatArchiveRepository organizerSeatArchiveRepository;
    @Autowired
    SeatTypeWithEventCustomCreator seatTypeWithEventCustomCreator;

    @Test
    public void saveSuccessfully() {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
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
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, -1);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
    }

    @Test
    public void saveTotalNumberOfSeatsLessThanAvailable() {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, 2);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
    }

    @Test
    public void getById(){
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, 1);

        this.organizerSeatArchiveRepository.save(organizerSeatArchive);

        Optional<OrganizerSeatArchive> resultOrganizerSeatArchiveOptional
                = this.organizerSeatArchiveRepository.findBySeatTypeId(seatType.getId());
        Assertions.assertEquals(resultOrganizerSeatArchiveOptional.get(), organizerSeatArchive);
    }
}
