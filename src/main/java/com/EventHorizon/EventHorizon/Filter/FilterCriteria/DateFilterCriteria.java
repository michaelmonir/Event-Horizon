package com.EventHorizon.EventHorizon.Filter.FilterCriteria;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
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
    public Specification<Event> meetCriteria() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("eventDate"), "%" + strDate + "%");
    }
}
