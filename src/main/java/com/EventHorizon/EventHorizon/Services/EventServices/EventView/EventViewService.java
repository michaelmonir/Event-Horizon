package com.EventHorizon.EventHorizon.Services.EventServices.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventViewService {

    @Autowired
    private UnregisteredUserChain unregisteredUserChain;
    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;

    public EventViewDto getEventViewDto(int eventId, int userId) {
        Event event = eventRepositoryServiceInterface.getById(eventId);
        return this.unregisteredUserChain.getDto(event, userId);
    }
}
