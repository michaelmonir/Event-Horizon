package com.EventHorizon.EventHorizon.EventCreation.EventDto;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import lombok.Data;

import java.util.Date;
@Data
public class EventDetailsDto {
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private Location eventLocation;

    public EventDetailsDto(Event event) {
        this.name = event.getName();
        this.eventCategory = event.getEventCategory();
        this.eventDate = event.getEventDate();
        this.description = event.getDescription();
        this.eventLocation = event.getEventLocation();
    }

    @Override
    public String toString() {
        return "EventDetailsDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", eventCategory='" + eventCategory + '\'' +
                ", eventDate=" + eventDate +
                ", eventLocation=" + eventLocation +
                '}';
    }
}
