package com.EventHorizon.EventHorizon.Services.Event.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventViewService {

    @Autowired
    private UnregisteredUserChain unregisteredUserChain;
    @Autowired
    private EventRepositoryServiceFacade eventRepositoryServiceFacade;

    public EventViewDto getEventViewDto(int eventId, int userId) {
        Event event = eventRepositoryServiceFacade.getByIdAndHandleNotFound(eventId);
        return this.unregisteredUserChain.getDto(event, userId);
    }
}
