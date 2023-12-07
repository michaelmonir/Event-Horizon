package com.EventHorizon.EventHorizon.RepositoryServices.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.SuperEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedLaunchedEventDtoMapper implements DetailedEventDtoMapper {
    @Autowired
    private AdsOptionDtoMapper adsOptionDtoMapper;

    public LaunchedEvent getEventFromDetailedEventDTO(DetailedEventDto dto) {
        DetailedLaunchedEventDto detailedLaunchedEventDto=(DetailedLaunchedEventDto)dto;
        Event event = Event.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .eventCategory(dto.getEventCategory())
                .eventDate(dto.getEventDate())
                .eventAds(this.adsOptionDtoMapper.getAdsOptionFromDTO(dto.getEventAds()))
                .eventLocation(dto.getEventLocation())
                .build();
        return LaunchedEvent.builder().event(event).launchedDate(detailedLaunchedEventDto.getLaunchedDate()).id(detailedLaunchedEventDto.getId()).build();
    }

    public DetailedLaunchedEventDto getDTOfromDetailedEvent(SuperEvent event) {
        LaunchedEvent launchedEvent=(LaunchedEvent) event;
        return new DetailedLaunchedEventDto(launchedEvent);
    }
}
