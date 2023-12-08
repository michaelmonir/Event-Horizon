package com.EventHorizon.EventHorizon.Repository.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SponsorSeatArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SponsorSeatArchiveRepository  extends JpaRepository<SponsorSeatArchive, Integer>
{
    SponsorSeatArchive save(SponsorSeatArchive sponsorSeatArchive);

    Optional<SponsorSeatArchive> findBySeatTypeIdAndSponsorId(int seatTypeId, int sponsorId);
}
