package com.EventHorizon.EventHorizon.Dashboard;


import com.EventHorizon.EventHorizon.EventCreation.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.EventCreation.EventService.EventService;
import com.EventHorizon.EventHorizon.Exceptions.InvalidPageIndex;
import com.EventHorizon.EventHorizon.Exceptions.InvalidPageSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Dashboard {
    private List<EventHeaderDto> eventHeaderDtos;
    @Autowired
    private EventService eventService;
    int pageSize;
    PageRequest pageWithRecords;

    public List<EventHeaderDto> getPage(int pageIndex, int pageSize) {
        if (pageIndex < 0)
            throw new InvalidPageIndex();
        if (pageSize < 1)
            throw new InvalidPageSize();
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "eventDate"));
        eventHeaderDtos = eventService.getAllEventsHeaderDto(pageWithRecords);
        return eventHeaderDtos;
    }
}
