package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class CityFilterCriteria implements FilterCriteria {

    private String city;

    @Override
    public Specification<Event> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("eventLocation").get("city"), "%" + city + "%");
    }
}
