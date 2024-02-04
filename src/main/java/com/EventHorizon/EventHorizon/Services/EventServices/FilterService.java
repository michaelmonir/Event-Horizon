package com.EventHorizon.EventHorizon.Services.EventServices;


import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Filter.FilterCriteriaInterface;
import com.EventHorizon.EventHorizon.Filter.Factories.FilterFactory;
import com.EventHorizon.EventHorizon.Filter.FilterRelationList;
import com.EventHorizon.EventHorizon.Filter.Factories.RelationTypeFactory;
import com.EventHorizon.EventHorizon.Repository.Event.EventRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.DashboardRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterFactory filterFactory;
    private final EventRepository eventRepository;
    private final RelationTypeFactory relationTypeFactory;
    private final DashboardRepositoryService dashboardRepositoryService;

    private Specification<Event> getSpecificationForAll() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("id"), -1);
    }

    public Specification<Event> getSpecifications(List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters, FilterEntityType entityType) {
        Specification<Event> specification = getSpecificationForAll();
        for (FilterRelationList<FilterTypes, FilterRelation, Object> filter : filters) {
            FilterCriteriaInterface filterCriteria = filterFactory.getFilterCriteria(filter);
            FilterCriteriaInterface relationCriteria = relationTypeFactory.getRelationCriteria(specification, filter.second, filterCriteria);
            specification = relationCriteria.meetCriteria(entityType);
        }
        return specification;
    }

    public List<? extends Event> getFilteredEvents(List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters) {
        return eventRepository.findAll(getSpecifications(filters, FilterEntityType.EVENT));
    }

    public List<EventHeaderDto> getFilteredEventHeadersList(int pageIndex, int pageSize, List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters) {
        return this.dashboardRepositoryService.getFilteredPage(pageIndex, pageSize, getSpecifications(filters, FilterEntityType.EVENT));
    }

}
