package com.EventHorizon.EventHorizon.Services.Event;


import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageIndexException;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageSizeException;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterEntityType;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Filter.FilterRelationList;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.EventViewType;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.FilterRepositoryService;
import org.hibernate.type.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private FilterRepositoryService filterRepositoryService;
    @Autowired
    private FilterService filterService;

    public List<EventHeaderDto> getFilteredPage(int pageIndex, int pageSize
            , List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters
            , EventViewType eventViewType ) {
        checkPageIndexAndSize(pageIndex, pageSize);

        PageRequest pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"));

        Specification specification = filterService.getSpecifications(filters, this.getFilterEntityType(eventViewType));
        return filterRepositoryService.getFilteredEventsHeaderDto(pageWithRecords, specification, EventViewType.LAUNCHED);
    }

    private void checkPageIndexAndSize(int pageIndex, int pageSize) {
        if (pageIndex < 0)
            throw new InvalidPageIndexException();
        if (pageSize < 1)
            throw new InvalidPageSizeException();
    }

    private FilterEntityType getFilterEntityType(EventViewType eventViewType) {
        switch (eventViewType) {
            case DRAFTED:
                return FilterEntityType.EVENT;
            case LAUNCHED:
                return FilterEntityType.EVENT;
            case GOING:
                return FilterEntityType.CLIENT_GOING_VIEW;
            default: return null;
        }
    }

//    public List<EventHeaderDto> getFilteredPage(int pageIndex, int pageSize, Specification<Event> specification) {
//        checkPageIndexAndSize(pageIndex, pageSize);
//
//        PageRequest pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"));
//        return filterRepositoryService.getFilteredEventsHeaderDto(pageWithRecords, specification, EventViewType.LAUNCHED);
//    }

}
