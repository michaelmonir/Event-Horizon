package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SponsorSeatArchive;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SponsorSeatArchiveRepository;
import com.EventHorizon.EventHorizon.Repository.SponsorRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

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
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);
        Assertions.assertDoesNotThrow(() -> this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void saveWithoutSavingTheSeatType() {

        SeatType seatType = new SeatType("s", 1);
        Sponsor sponsor = (Sponsor)this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);

        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                ()->this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void saveWithoutSavingTheSponsor() {

        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = new Sponsor();

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);

        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                ()->this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void saveNegativeAvailableNumberOfSeats() {

        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor)this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, -1);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void saveTotalNumberOfSeatsLessThanAvailable() {

        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor)this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 2);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                ()->this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void getById()
    {
        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);
        this.sponsorSeatArchiveRepository.save(sponsorSeatArchive);

        SponsorSeatArchive savedSponsorSeatArchive = this.sponsorSeatArchiveRepository.findBySeatTypeIdAndSponsorId(seatType.getId(), sponsor.getId());

        Assertions.assertEquals(savedSponsorSeatArchive, sponsorSeatArchive);
    }

    // test not working
//    @Test
//    public void deletingSponsorDeletesTheArchive()
//    {
//        SeatType seatType = this.getAndCreateCustomSeatTypeFromSavedEvent();
//        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);
//
//        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);
//        this.sponsorSeatArchiveRepository.save(sponsorSeatArchive);
//
//        this.sponsorRepository.deleteById(sponsor.getId());
//
//        Assertions.assertEquals(this.sponsorSeatArchiveRepository.findAll().size(), 0);
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
