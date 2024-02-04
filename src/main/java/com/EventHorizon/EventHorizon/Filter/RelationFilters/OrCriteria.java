package com.EventHorizon.EventHorizon.Filter.RelationFilters;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class OrCriteria implements FilterCriteriaInterface {

    private Specification<Event> last;
    private FilterCriteriaInterface otherCriteria;

    @Override
    public Specification<Event> meetCriteria(FilterEntityType entityType) {
        return last.or(otherCriteria.meetCriteria(entityType));
    }
}
