package com.EventHorizon.EventHorizon.Filter.FilterCriteria;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class EventTypeFilterCriteria implements FilterCriteriaInterface {
    private EventType eventType;

    @Override
    public Specification<Event> meetCriteria(FilterEntityType entityType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("eventType"), eventType);
    }
}
