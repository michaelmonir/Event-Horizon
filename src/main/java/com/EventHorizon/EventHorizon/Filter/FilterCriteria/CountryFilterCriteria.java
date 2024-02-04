package com.EventHorizon.EventHorizon.Filter.FilterCriteria;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class CountryFilterCriteria implements FilterCriteriaInterface {

    private String country;

    @Override
    public Specification<Event> meetCriteria(FilterEntityType entityType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("eventLocation").get("country"), "%" + country + "%");
    }
}
