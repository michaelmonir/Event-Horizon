package com.EventHorizon.EventHorizon.RepositoryServices.Event.Utility;


import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Views.ClientGoingView;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageIndexException;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageSizeException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.DraftedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.LaunchedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.EventViewType;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.FilterRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardRepositoryService {

    @Autowired
    FilterRepositoryService filterRepositoryService;

    public List<EventHeaderDto> getFilteredPage(int pageIndex, int pageSize, Specification<Event> specification) {
        checkPageIndexAndSize(pageIndex, pageSize);

        PageRequest pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"));
        return filterRepositoryService.getFilteredEventsHeaderDto(pageWithRecords, specification, EventViewType.LAUNCHED);
    }

    private void checkPageIndexAndSize(int pageIndex, int pageSize) {
        if (pageIndex < 0)
            throw new InvalidPageIndexException();
        if (pageSize < 1)
            throw new InvalidPageSizeException();
    }
}
