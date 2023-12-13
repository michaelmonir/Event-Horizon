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
@SuperBuilder
public  class DetailedEventDto {
    protected int id;
    protected String name;
    protected String description;
    protected String eventCategory;
    protected Date eventDate;
    protected EventType eventType;
    protected AdsOptionDto eventAds;
    protected Location eventLocation;
    protected OrganizerHeaderDto eventOrganizer;
}
