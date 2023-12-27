package com.EventHorizon.EventHorizon.Repository.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatTypeRepository extends JpaRepository<SeatType, Integer> {
    Optional<SeatType> findById(int id);

    List<SeatType> findAllByEventId(int eventId);

//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM seat_type WHERE event_id = :eventId", nativeQuery = true)
//    void deleteAllByEventId(@Param("eventId") int eventId);
    @Transactional
    void deleteByEventId(int eventId);

}
