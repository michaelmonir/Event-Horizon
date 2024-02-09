package com.EventHorizon.EventHorizon.Repository.Event;

import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.ViewEventInterface;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaunchedEventRepository
        extends JpaRepository<LaunchedEvent, Integer>, JpaSpecificationExecutor<LaunchedEvent>
        , ViewEventInterface<LaunchedEvent> {

    List<LaunchedEvent> findAll(@Nullable Specification<LaunchedEvent> specification);

    @Override
    Page<LaunchedEvent> findAll(Specification<LaunchedEvent> spec, Pageable pageable);
}
