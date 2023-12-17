package com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Mappers.AdsOptionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedLaunchedEventDtoMapper implements DetailedEventDtoMapper {
    @Autowired
    private AdsOptionDtoMapper adsOptionDtoMapper;

    public LaunchedEvent getEventFromDetailedEventDTO(DetailedEventDto dto) {
        DetailedLaunchedEventDto detailedLaunchedEventDto=(DetailedLaunchedEventDto)dto;
        return LaunchedEvent.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .eventCategory(dto.getEventCategory())
                .eventDate(dto.getEventDate())
                .eventAds(this.adsOptionDtoMapper.getAdsOptionFromDTO(dto.getEventAds()))
                .eventLocation(dto.getEventLocation())
                .launchedDate(detailedLaunchedEventDto.getLaunchedDate())
                .eventType(detailedLaunchedEventDto.getEventType())
                .build();
    }

    public DetailedLaunchedEventDto getDTOfromDetailedEvent(Event event) {
        LaunchedEvent launchedEvent=(LaunchedEvent) event;
        return new DetailedLaunchedEventDto(launchedEvent);
    }
}
