package com.EventHorizon.EventHorizon.RepositoryServices.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedDraftedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.SuperEvent;

public interface DetailedEventDtoMapper {
    public SuperEvent getEventFromDetailedEventDTO(DetailedEventDto dto) ;

    public DetailedEventDto getDTOfromDetailedEvent(SuperEvent event);
}
