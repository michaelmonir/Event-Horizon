package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Entities.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewEventDto {
    private int id;
    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private Location eventLocation;


    public ViewEventDto(Event event) {
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
