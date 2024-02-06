package com.EventHorizon.EventHorizon.Filter.Factories;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import com.EventHorizon.EventHorizon.Filter.RelationFilters.AndCriteria;
import com.EventHorizon.EventHorizon.Filter.RelationFilters.OrCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RelationTypeFactory {

    public FilterCriteriaInterface getRelationCriteria(Specification<Event> last, FilterRelation relation, FilterCriteriaInterface otherCriteria) {
        return switch (relation) {
            case AND -> new AndCriteria(last, otherCriteria);
            case OR -> new OrCriteria(last, otherCriteria);
        };
    }
}
