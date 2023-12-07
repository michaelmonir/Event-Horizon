package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SponsorSeatArchive;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SponsorSeatArchiveRepository;
import com.EventHorizon.EventHorizon.Repository.SponsorRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.SponsorInformationRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SponsorSeatArchiveRepositoryTest
{
    @Autowired
    UserCustomCreator userCustomCreator;
    @Autowired
    SponsorSeatArchiveRepository sponsorSeatArchiveRepository;
    @Autowired
    SeatTypeCustomCreator seatTypeCustomCreator;
    @Autowired
    EventCustomCreator eventCustomCreator;
    @Autowired
    EventRepositoryService eventRepositoryService;
    @Autowired
    SponsorRepository sponsorRepository;

    @Test
    public void saveSuccessfully() {

        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor)this.userCustomCreator.getUser(Role.SPONSOR);
        this.sponsorRepository.save(sponsor);


        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);

        Assertions.assertDoesNotThrow(()->this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

//    @Test
//    public void saveWithoutSavingTheSeatType() {
//
//        SeatType seatType = new SeatType("s", 1);
//        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, 1);
//
//        Assertions.assertThrows(DataIntegrityViolationException.class,
//                ()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
//    }
//
//    @Test
//    public void saveNegativeAvailableNumberOfSeats() {
//
//        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
//        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, -1);
//
//        Assertions.assertThrows(DataIntegrityViolationException.class,
//                ()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
//    }
//
//    @Test
//    public void saveTotalNumberOfSeatsLessThanAvailable() {
//
//        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
//        OrganizerSeatArchive organizerSeatArchive = new OrganizerSeatArchive(seatType, 1, 2);
//
//        Assertions.assertThrows(DataIntegrityViolationException.class,
//                ()->this.organizerSeatArchiveRepository.save(organizerSeatArchive));
//    }

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
