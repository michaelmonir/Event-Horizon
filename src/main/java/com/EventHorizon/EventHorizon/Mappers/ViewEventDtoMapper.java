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
    private SeatTypeListMapper seatTypeListMapper;

    public ViewEventDto getDTOfromViewEvent(LaunchedEvent event) {
        return ViewEventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .eventDate(event.getEventDate())
                .description(event.getDescription())
                .eventLocation(event.getEventLocation())
                .eventCategory(event.getEventCategory())
                .eventOrganizer(new OrganizerHeaderDto(event.getEventOrganizer()))
                .seatTypes(seatTypeListMapper.getSeatTypeDtoListFromSeatTypeList(event.getSeatTypes()))
                .build();
    }
}
