package com.EventHorizon.EventHorizon.Repository.Event;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.ViewEventInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftedEventRepository
        extends JpaRepository<DraftedEvent,Integer>, JpaSpecificationExecutor<DraftedEvent>,
        ViewEventInterface<DraftedEvent> {
    @Override
    Page<DraftedEvent> findAll(Specification<DraftedEvent> spec, Pageable pageable);
}
