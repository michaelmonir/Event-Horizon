package com.EventHorizon.EventHorizon.Repository.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizerSeatArchiveRepository extends JpaRepository<OrganizerSeatArchive, Integer>
{
    OrganizerSeatArchive save(OrganizerSeatArchive organizerSeatArchive);
    Optional<OrganizerSeatArchive> findBySeatTypeId(int seatTypeId);
}
