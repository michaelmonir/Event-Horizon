package com.EventHorizon.EventHorizon.Services.EventServices.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewOnlyChain {
    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;

    public EventViewDto getDto(Event event) {
        if (event.getEventType() == EventType.DRAFTEDEVENT)
            throw new InvalidAccessOfEventException();

        return eventViewDtoMapper.getDetailedDtoFromEvent(event, UserEventRole.VIEWONLY);
    }
}
