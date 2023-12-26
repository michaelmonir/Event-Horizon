package com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedEventDtoMapperInterface
{
    @Autowired
    private DetailedEventDtoMapperFactory detailedEventDtoMapperFactory;

    public Event getEventFromDetailedEventDTO(DetailedEventDto dto) {
        DetailedEventDtoMapper mapper = detailedEventDtoMapperFactory.getEventDtoMapperByEventType(dto.getEventType());
        return mapper.getEventFromDetailedEventDTO(dto);
    }

    public DetailedEventDto getDTOfromDetailedEvent(Event event) {
        DetailedEventDtoMapper mapper = detailedEventDtoMapperFactory.getEventDtoMapperByEventType(event.getEventType());
        return mapper.getDTOfromDetailedEvent(event);
    }

    public void updateEventFromDetailedEventDTO(Event event, DetailedEventDto dto) {
        DetailedEventDtoMapper mapper = detailedEventDtoMapperFactory.getEventDtoMapperByEventType(dto.getEventType());
        mapper.updateEventFromDetailedEventDTO(event, dto);
    }
}
