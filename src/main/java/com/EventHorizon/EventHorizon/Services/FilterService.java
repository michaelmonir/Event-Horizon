package com.EventHorizon.EventHorizon.Services;


import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Filter.FilterCriteria;
import com.EventHorizon.EventHorizon.Filter.FilterFactory;
import com.EventHorizon.EventHorizon.Filter.FilterRelationList;
import com.EventHorizon.EventHorizon.Filter.RelationTypeFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.DashboardRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterFactory filterFactory;
    private final LaunchedEventRepositoryService LaunchEventRepositoryService;
    private final RelationTypeFactory relationTypeFactory;
    private final DashboardRepositoryService dashboardRepositoryService;

    private Specification<Event> getSpecificationForAll() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("id"), -1);
    }

    public Specification<Event> getSpecifications(List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters) {
        Specification<Event> specification = getSpecificationForAll();
        for (FilterRelationList<FilterTypes, FilterRelation, Object> filter : filters) {
            FilterCriteria filterCriteria = filterFactory.getFilterCriteria(filter);
            FilterCriteria relationCriteria = relationTypeFactory.getRelationCriteria(specification, filter.second, filterCriteria);
            specification = relationCriteria.meetCriteria();
        }
        return specification;
    }

    public List<? extends Event> getFilteredEvents(List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters) {
        return LaunchEventRepositoryService.getAllEvents(getSpecifications(filters));
    }
    public List<EventHeaderDto> getFilteredEventHeadersList(int pageIndex, int pageSize, List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters) {
        return this.dashboardRepositoryService.getFilteredPage(pageIndex, pageSize, getSpecifications(filters));
    }

}
