package com.EventHorizon.EventHorizon.Repository.Event;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftedEventRepository extends JpaRepository<DraftedEvent,Integer>, JpaSpecificationExecutor<DraftedEvent> {
}
