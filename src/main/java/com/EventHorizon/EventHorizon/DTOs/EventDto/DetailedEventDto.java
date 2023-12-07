package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class DetailedEventDto {
    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private EventType eventType;
    private AdsOptionDto eventAds;
    private Location eventLocation;
    private OrganizerHeaderDto eventOrganizer;
}
