package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;


import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageIndexException;
import com.EventHorizon.EventHorizon.Exceptions.PagingExceptions.InvalidPageSizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardRepositoryService {
    private List<EventHeaderDto> eventHeaderDtos;
    @Autowired
    private EventRepositoryService eventRepositoryService;
    int pageSize;
    PageRequest pageWithRecords;

    public List<EventHeaderDto> getPage(int pageIndex, int pageSize) {
        if (pageIndex < 0)
            throw new InvalidPageIndexException();
        if (pageSize < 1)
            throw new InvalidPageSizeException();
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"));
        eventHeaderDtos = eventRepositoryService.getAllEventsHeaderDto(pageWithRecords);
        return eventHeaderDtos;
    }
}
