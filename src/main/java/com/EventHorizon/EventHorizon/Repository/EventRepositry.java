package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepositry extends JpaRepository<Event,Integer> {

}
