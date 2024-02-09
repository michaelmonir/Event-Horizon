package com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterRepositoryService {

    @Autowired
    private ViewRepositoryServiceFactory viewRepositoryServiceFactory;

    public List<EventHeaderDto> getFilteredEventsHeaderDto(PageRequest pageWithRecords, Specification<Event> specification, EventViewType viewType) {
        ViewEventInterface viewEventInterface
                = viewRepositoryServiceFactory.getViewRepositoryService(viewType);

        List<HeaderInterface> eventHeaderInterfaces = viewEventInterface.findAll(specification, pageWithRecords).getContent();
        return this.getHeadersListFromEventsList(eventHeaderInterfaces);
    }

    private List<EventHeaderDto> getHeadersListFromEventsList(List<HeaderInterface> eventHeaderInterfaces) {
        List<EventHeaderDto> eventHeaderDtos = new ArrayList<>();
        for (HeaderInterface eventHeaderInterface : eventHeaderInterfaces) {
            eventHeaderDtos.add(eventHeaderInterface.getHeader());
        }
        return eventHeaderDtos;
    }
}
