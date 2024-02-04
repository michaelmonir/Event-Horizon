package com.EventHorizon.EventHorizon.Repository.Event;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {

    @Override
    List<Event> findAll(@Nullable Specification<Event> specification);

}
