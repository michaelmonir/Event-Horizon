package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class CategoryFilterCriteria implements FilterCriteria {

    private String category;

    @Override
    public Specification<Event> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("eventCategory"), "%" + category + "%");
    }
}
