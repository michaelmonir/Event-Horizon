package com.EventHorizon.EventHorizon.Repository.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.OrganizerSeatArchive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizerSeatArchiveRepository extends JpaRepository<OrganizerSeatArchive, Integer>
{
    OrganizerSeatArchive save(OrganizerSeatArchive organizerSeatArchive);
    Optional<OrganizerSeatArchive> findBySeatTypeId(int seatTypeId);

    @Query("SELECT osa FROM OrganizerSeatArchive osa JOIN osa.seatType st WHERE st.event.id = :eventId")
    List<OrganizerSeatArchive> findAllByEventId(@Param("eventId") int eventId);
}
