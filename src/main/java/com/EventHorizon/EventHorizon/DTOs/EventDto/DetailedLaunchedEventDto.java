package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventRelated.AdsOptionDto;
import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Date;

@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailedLaunchedEventDto extends DetailedEventDto{

    private Date launchedDate;

    public DetailedLaunchedEventDto(LaunchedEvent event) {
        this.setId(event.getId());
        this.setName(event.getName());
        this.setDescription(event.getDescription());
        this.setEventCategory(event.getEventCategory());
        this.setEventDate(event.getEventDate());
        this.launchedDate=event.getLaunchedDate();
        this.setEventLocation(event.getEventLocation());
        this.setEventAds(new AdsOptionDto(event.getEventAds()));
        this.setEventType(event.getEventType());
        this.setEventOrganizer(new OrganizerHeaderDto(event.getEventOrganizer()));
        this.initializeSeatTypeListFromEvent(event);
    }

    private void initializeSeatTypeListFromEvent(Event event) {
        this.seatTypes = new ArrayList<>();
        event.getSeatTypes().forEach(seatType -> {
            this.seatTypes.add(new SeatTypeDto(seatType));
        });
    }
}
