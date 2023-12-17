package com.EventHorizon.EventHorizon.Mappers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewEventDtoMapper {
    @Autowired
    private AdsOptionDtoMapper adsOptionDtoMapper;
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;

    public LaunchedEvent getEventFromViewEventDTO(ViewEventDto dto) {
        return LaunchedEvent.builder()
                .name(dto.getName())
                .id(dto.getId())
                .description(dto.getDescription())
                .eventCategory(dto.getEventCategory())
                .eventDate(dto.getEventDate())
                .eventLocation(dto.getEventLocation())
                .eventAds(launchedEventRepositoryService.getById(dto.getId()).getEventAds())
                .build();
    }

    public ViewEventDto getDTOfromViewEvent(LaunchedEvent event) {
        return ViewEventDto.builder()
                .id(event.getId())
                .eventOrganizer(new OrganizerHeaderDto(event.getEventOrganizer()))
                .eventDate(event.getEventDate())
                .name(event.getName())
                .eventLocation(event.getEventLocation())
                .eventCategory(event.getEventCategory())
                .description(event.getDescription())
                .build();
    }
}
