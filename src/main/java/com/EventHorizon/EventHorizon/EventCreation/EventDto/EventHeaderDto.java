package com.EventHorizon.EventHorizon.EventCreation.EventDto;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import lombok.Data;

import java.util.Date;
@Data
public class EventHeaderDto {
    private String name;
    private String eventCategory;
    private Date eventDate;
    private Location eventLocation;

    public EventHeaderDto(Event event) {
        this.name = event.getName();
        this.eventCategory = event.getEventCategory();
        this.eventDate = event.getEventDate();
        this.eventLocation = event.getEventLocation();
    }

    @Override
    public String toString() {
        return "EventHeaderDto{" +
                "name='" + name + '\'' +
                ", eventCategory='" + eventCategory + '\'' +
                ", eventDate=" + eventDate +
                ", eventLocation=" + eventLocation +
                '}';
    }

}
