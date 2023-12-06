package com.EventHorizon.EventHorizon.DTOs.EventDto;

import com.EventHorizon.EventHorizon.DTOs.UserDto.OrganizerHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetailedEventDto {
    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private EventType eventType;
    private AdsOptionDto eventAds;
    private Location eventLocation;
    private OrganizerHeaderDto eventOrganizer;

    public  DetailedEventDto(LaunchedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.eventCategory = event.getEventCategory();
        this.eventDate = event.getEventDate();
        this.eventLocation = event.getEventLocation();
        this.eventAds = new AdsOptionDto(event.getEventAds());
        this.eventType=event.getEventType();
        this.eventOrganizer = new OrganizerHeaderDto(event.getEventOrganizer());
    }
}
