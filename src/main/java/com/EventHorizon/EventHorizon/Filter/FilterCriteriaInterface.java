package com.EventHorizon.EventHorizon.Filter;

import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import org.springframework.data.jpa.domain.Specification;

public interface FilterCriteriaInterface {

    Specification meetCriteria(FilterEntityType entityType);
}
