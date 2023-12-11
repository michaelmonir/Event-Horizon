package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class OrCriteria implements FilterCriteria {

    private Specification<Event> last;
    private FilterCriteria otherCriteria;

    @Override
    public Specification<Event> meetCriteria() {
        return last.or(otherCriteria.meetCriteria());
    }
}
