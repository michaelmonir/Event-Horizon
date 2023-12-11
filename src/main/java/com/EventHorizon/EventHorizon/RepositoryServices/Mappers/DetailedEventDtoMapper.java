package com.EventHorizon.EventHorizon.RepositoryServices.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;

public interface DetailedEventDtoMapper {
    public Event getEventFromDetailedEventDTO(DetailedEventDto dto) ;

    public DetailedEventDto getDTOfromDetailedEvent(Event event);
}
