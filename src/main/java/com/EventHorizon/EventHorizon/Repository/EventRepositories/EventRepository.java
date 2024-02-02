package com.EventHorizon.EventHorizon.Repository.EventRepositories;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {

    @Override
    List<Event> findAll(@Nullable Specification<Event> specification);

//    @Query("SELECT e, " +
//            "CASE WHEN e.eventType = 1 THEN le ELSE de END " +
//            "FROM Event e " +
//            "LEFT JOIN LaunchedEvent le ON e.id = le.id AND e.eventType = 1 " +
//            "LEFT JOIN DraftedEvent de ON e.id = de.id AND e.eventType <> 1 " +
//            "WHERE e.id = :eventId")
//    Optional<Event> findEventById(@Param("eventId") Long eventId);
}
