package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class OrganizerIdFilterCriteria implements FilterCriteria {

    private int id;

    @Override
    public Specification<Event> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("eventOrganizer").get("information").get("id")
                        , this.id);
    }
}
