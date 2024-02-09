package com.EventHorizon.EventHorizon.Filter.FilterCriteria;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.Factories.RootFactory;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public class DateFilterCriteria implements FilterCriteriaInterface {

    private Date date;

    @Override
    public Specification meetCriteria(FilterEntityType entityType) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(RootFactory.getRoot(entityType,root).get("eventDate"), "%" + strDate + "%");
    }
}
