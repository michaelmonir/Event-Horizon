package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import org.springframework.data.jpa.domain.Specification;

public interface FilterCriteria {

    Specification<Event> meetCriteria();
}
