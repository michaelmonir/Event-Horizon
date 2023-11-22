package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Entities.Location;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
public class ViewEventDTO {
    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private Location eventLocation;

    public ViewEventDTO()
    {

    }

    public ViewEventDTO(Event event) {
        this.id = event.getId();
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
