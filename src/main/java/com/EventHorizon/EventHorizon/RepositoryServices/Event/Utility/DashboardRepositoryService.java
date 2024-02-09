package com.EventHorizon.EventHorizon.RepositoryServices.Event.Utility;


import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Views.ClientGoingView;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageIndexException;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageSizeException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.DraftedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.LaunchedEventRepositoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardRepositoryService {
    private List<EventHeaderDto> eventHeaderDtos;
    @Autowired
    private LaunchedEventRepositoryServiceImpl launchedEventRepositoryServiceImpl;
    @Autowired
    private DraftedEventRepositoryServiceImpl draftedEventRepositoryServiceImpl;
    int pageSize;
    PageRequest pageWithRecords;
    private void checkPageIndexAndSize(int pageIndex, int pageSize) {
        if (pageIndex < 0)
            throw new InvalidPageIndexException();
        if (pageSize < 1)
            throw new InvalidPageSizeException();
    }
    public List<EventHeaderDto> getPage(int pageIndex, int pageSize) {
        checkPageIndexAndSize(pageIndex, pageSize);
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"));
        eventHeaderDtos = launchedEventRepositoryServiceImpl.getAllEventsHeaderDto(pageWithRecords);
        return eventHeaderDtos;
    }

    public List<EventHeaderDto> getFilteredPage(int pageIndex, int pageSize, Specification<Event> specification) {
        checkPageIndexAndSize(pageIndex, pageSize);
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"));
        eventHeaderDtos = launchedEventRepositoryServiceImpl.getFilteredEventsHeaderDto(pageWithRecords, specification);
        return eventHeaderDtos;
    }
    public List<EventHeaderDto> getFilteredPageFromClientGoingView(int pageIndex, int pageSize, Specification<ClientGoingView> specification) {
        checkPageIndexAndSize(pageIndex, pageSize);
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize);
        eventHeaderDtos = launchedEventRepositoryServiceImpl.getFilteredEventsHeaderDtoFromClientGoingView(pageWithRecords, specification);
        return eventHeaderDtos;
    }

}
