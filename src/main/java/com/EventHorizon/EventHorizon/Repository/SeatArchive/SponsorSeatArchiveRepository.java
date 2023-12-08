package com.EventHorizon.EventHorizon.Repository.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SponsorSeatArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorSeatArchiveRepository  extends JpaRepository<SponsorSeatArchive, Integer>
{
    SponsorSeatArchive save(SponsorSeatArchive sponsorSeatArchive);

    SponsorSeatArchive findBySeatTypeIdAndSponsorId(int seatTypeId, int sponsorId);
}
