package com.EventHorizon.EventHorizon.Mappers.DetailedEventDtos;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventRelated.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Mappers.AdsOptionDtoMapper;
import com.EventHorizon.EventHorizon.Mappers.SeatTypeListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedLaunchedEventDtoMapper implements DetailedEventDtoMapper {
    @Autowired
    private AdsOptionDtoMapper adsOptionDtoMapper;
    @Autowired
    private SeatTypeListMapper seatTypeListMapper;

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
                .seatTypes(seatTypeListMapper.getSeatTypeListFromSeatTypeListDTO(dto.getSeatTypes()))
                .build();
    }

    public DetailedLaunchedEventDto getDTOfromDetailedEvent(Event event) {
        LaunchedEvent launchedEvent=(LaunchedEvent) event;

        return DetailedLaunchedEventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .eventCategory(event.getEventCategory())
                .eventDate(event.getEventDate())
                .launchedDate(launchedEvent.getLaunchedDate())
                .eventLocation(event.getEventLocation())
                .eventAds(new AdsOptionDto(event.getEventAds()))
                .eventType(event.getEventType())
                .eventOrganizer(new OrganizerHeaderDto(event.getEventOrganizer()))
                .seatTypes(seatTypeListMapper.getSeatTypeDtoListFromSeatTypeList(event.getSeatTypes()))
                .build();
    }

    // shouldn't update seat types
    public void updateEventFromDetailedEventDTO(Event event, DetailedEventDto dto) {
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setEventCategory(dto.getEventCategory());
        event.setEventDate(dto.getEventDate());
        event.setEventLocation(dto.getEventLocation());
        event.setEventAds(this.adsOptionDtoMapper.getAdsOptionFromDTO(dto.getEventAds()));
    }
}
