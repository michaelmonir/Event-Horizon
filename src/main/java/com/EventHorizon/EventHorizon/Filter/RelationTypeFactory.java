package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RelationTypeFactory {

    public FilterCriteria getRelationCriteria(Specification<Event> last, FilterRelation relation, FilterCriteria otherCriteria) {
        return switch (relation) {
            case AND -> new AndCriteria(last, otherCriteria);
            case OR -> new OrCriteria(last, otherCriteria);
        };
    }
}
