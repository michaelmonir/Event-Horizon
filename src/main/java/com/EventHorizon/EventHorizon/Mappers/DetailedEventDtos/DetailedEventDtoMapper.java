package com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;

public interface DetailedEventDtoMapper {
    Event getEventFromDetailedEventDTO(DetailedEventDto dto) ;

    DetailedEventDto getDTOfromDetailedEvent(Event event);

    public void updateEventFromDetailedEventDTO(Event event, DetailedEventDto dto);
}
