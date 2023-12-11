package com.EventHorizon.EventHorizon.Repository.SeatArchiveTests;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SponsorSeatArchive;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.SeatTypeWithEventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Repository.SeatArchive.SponsorSeatArchiveRepository;
import com.EventHorizon.EventHorizon.Repository.SponsorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;

@SpringBootTest
public class SponsorSeatTypeRepositoryTest {
    @Autowired
    UserCustomCreator userCustomCreator;
    @Autowired
    SponsorSeatArchiveRepository sponsorSeatArchiveRepository;
    @Autowired
    SeatTypeWithEventCustomCreator seatTypeWithEventCustomCreator;
    @Autowired
    SponsorRepository sponsorRepository;

    @Test
    public void saveSuccessfully() {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);
        Assertions.assertDoesNotThrow(() -> this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void saveWithoutSavingTheSeatType() {
        SeatType seatType = new SeatType("s", 1);
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);

        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void saveWithoutSavingTheSponsor() {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = new Sponsor();

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);

        Assertions.assertThrows(InvalidDataAccessApiUsageException.class,
                () -> this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void saveNegativeAvailableNumberOfSeats() {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, -1);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void saveTotalNumberOfSeatsLessThanAvailable() {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 2);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> this.sponsorSeatArchiveRepository.save(sponsorSeatArchive));
    }

    @Test
    public void getById() {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);
        this.sponsorSeatArchiveRepository.save(sponsorSeatArchive);

        Optional<SponsorSeatArchive> savedSponsorSeatArchiveOptional
                = this.sponsorSeatArchiveRepository.findBySeatTypeIdAndSponsorId(seatType.getId(), sponsor.getId());

        Assertions.assertEquals(savedSponsorSeatArchiveOptional.get(), sponsorSeatArchive);
    }

    @Test
    public void deletingSponsorDeletesTheArchive() {
        SeatType seatType = this.seatTypeWithEventCustomCreator.getAndCreateCustomSeatTypeFromSavedEvent();
        Sponsor sponsor = (Sponsor) this.userCustomCreator.getUserAndSaveInRepository(Role.SPONSOR);

        SponsorSeatArchive sponsorSeatArchive = new SponsorSeatArchive(seatType, sponsor, 1, 1);
        this.sponsorSeatArchiveRepository.save(sponsorSeatArchive);

        this.sponsorRepository.deleteById(sponsor.getId());

        Optional<SponsorSeatArchive> sponsorSeatArchiveOptional
                = this.sponsorSeatArchiveRepository.findBySeatTypeIdAndSponsorId(seatType.getId(), sponsor.getId());

        Assertions.assertFalse(sponsorSeatArchiveOptional.isPresent());
    }
}
