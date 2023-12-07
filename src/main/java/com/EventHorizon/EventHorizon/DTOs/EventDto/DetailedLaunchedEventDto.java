package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode
@Builder
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
    }
}
