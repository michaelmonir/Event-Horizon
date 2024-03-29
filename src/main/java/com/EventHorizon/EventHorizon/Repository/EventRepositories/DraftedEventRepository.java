package com.EventHorizon.EventHorizon.Repository.EventRepositories;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftedEventRepository extends JpaRepository<DraftedEvent,Integer>, JpaSpecificationExecutor<DraftedEvent> {
}
