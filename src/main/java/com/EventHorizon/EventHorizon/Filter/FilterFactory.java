package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FilterFactory {

    public FilterCriteria getFilterCriteria(FilterRelationList<FilterTypes, FilterRelation, Object> filter) {
        return switch (filter.first) {
            case ADDRESS -> new AddressFilterCriteria((String) filter.third);
            case CATEGORY -> new CategoryFilterCriteria((String) filter.third);
            case COUNTRY -> new CountryFilterCriteria((String) filter.third);
            case CITY -> new CityFilterCriteria((String) filter.third);
            case DATE -> new DateFilterCriteria((Date) filter.third);
            case ORGANIZER -> new OrganizerNameFilterCriteria((String) filter.third);
            case NAME -> new EventNameFilterCriteria((String) filter.third);
            case EVENTTYPE -> new EventTypeFilterCriteria((EventType) filter.third);
        };
    }
}
