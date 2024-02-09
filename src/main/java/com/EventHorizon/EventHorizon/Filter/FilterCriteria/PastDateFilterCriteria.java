package com.EventHorizon.EventHorizon.Filter.FilterCriteria;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.Factories.RootFactory;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

@AllArgsConstructor
public class PastDateFilterCriteria implements FilterCriteriaInterface {

    private Date date;

    @Override
    public Specification meetCriteria(FilterEntityType entityType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(RootFactory.getRoot(entityType, root).get("eventDate"), date);
    }
}
