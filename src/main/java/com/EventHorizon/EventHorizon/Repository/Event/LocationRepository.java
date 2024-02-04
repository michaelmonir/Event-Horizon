package com.EventHorizon.EventHorizon.Repository.Event;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Location WHERE id = (SELECT location_id FROM Event WHERE id = :eventId)", nativeQuery = true)
    void deleteLocationByEventId(@Param("eventId") int eventId);


}
