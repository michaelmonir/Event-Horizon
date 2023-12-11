package com.EventHorizon.EventHorizon.Services;


import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Filter.FilterCriteria;
import com.EventHorizon.EventHorizon.Filter.FilterFactory;
import com.EventHorizon.EventHorizon.Filter.FilterRelationList;
import com.EventHorizon.EventHorizon.Filter.RelationTypeFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterFactory filterFactory;
    private final EventRepositoryService eventRepositoryService;
    private final RelationTypeFactory relationTypeFactory;

    private Specification<Event> getSpecificationForAll() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("id"), -1);
    }

    private Specification<Event> getSpecifications(List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters) {
        Specification<Event> specification = getSpecificationForAll();
        for (FilterRelationList<FilterTypes, FilterRelation, Object> filter : filters) {
            FilterCriteria filterCriteria = filterFactory.getFilterCriteria(filter);
            FilterCriteria relationCriteria = relationTypeFactory.getRelationCriteria(specification, filter.second, filterCriteria);
            specification = relationCriteria.meetCriteria();
        }
        return specification;
    }

    public List<Event> getFilteredEvents(List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters) {
        return eventRepositoryService.getAllEvents(getSpecifications(filters));
    }
}
