package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FilterFactory {

    public FilterCriteria getFilterCriteria(FilterRelationList<FilterTypes, FilterRelation, Object> filter) {
        return switch (filter.first) {
            case ADDRESS -> new AddressFilterCriteria((String) filter.Third);
            case CATEGORY -> new CategoryFilterCriteria((String) filter.Third);
            case COUNTRY -> new CountryFilterCriteria((String) filter.Third);
            case CITY -> new CityFilterCriteria((String) filter.Third);
            case DATE -> new DateFilterCriteria((Date) filter.Third);
            case ORGANIZER -> new OrganizerNameFilterCriteria((String) filter.Third);
            case NAME -> new EventNameFilterCriteria((String) filter.Third);
        };
    }
}
