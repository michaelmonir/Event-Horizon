package com.EventHorizon.EventHorizon.Services.EventServices.EventView;


import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Mappers.EventViewDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserChain
{
    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;

    public EventViewDto getDto(Event event) {
        if (event.getEventType() == EventType.DRAFTEDEVENT)
            throw new InvalidAccessOfEventException();
        return eventViewDtoMapper.getDetailedDtoFromEvent(event);
    }
}
