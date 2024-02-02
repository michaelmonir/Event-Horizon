package com.EventHorizon.EventHorizon.Mappers.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Mappers.SeatTypes.SeatTypeListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventViewDtoMapper
{
    @Autowired
    private SeatTypeListMapper seatTypeListMapper;
    public EventViewDto getDetailedEventFromEventDto(Event event){
        EventViewDto eventViewDto = this.getEventViewFromCommonAttributes(event);

        if (event.getEventType() == EventType.LAUNCHEDEVENT)
            this.setAttributesForLaunchedEvent((LaunchedEvent) event, eventViewDto);
        return eventViewDto;
    }

    private EventViewDto getEventViewFromCommonAttributes(Event event){
        return EventViewDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .eventCategory(event.getEventCategory())
                .eventDate(event.getEventDate())
                .eventLocation(event.getEventLocation())
                .eventAds(new AdsOptionDto(event.getEventAds()))
                .eventType(event.getEventType())
                .eventOrganizer(new OrganizerHeaderDto(event.getEventOrganizer()))
                .seatTypes(seatTypeListMapper.getSeatTypeDtoListFromSeatTypeList(event.getSeatTypes()))
                .build();
    }

    private void setAttributesForLaunchedEvent(LaunchedEvent event, EventViewDto eventViewDto) {
        eventViewDto.setLaunchedDate(event.getLaunchedDate());
    }
}
