package com.EventHorizon.EventHorizon.Filter.FilterCriteria;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class CategoryFilterCriteria implements FilterCriteriaInterface {

    private String category;

    @Override
    public Specification<Event> meetCriteria(FilterEntityType entityType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("eventCategory"), "%" + category + "%");
    }
}
