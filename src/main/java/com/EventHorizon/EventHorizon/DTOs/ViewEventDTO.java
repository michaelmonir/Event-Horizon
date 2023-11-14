package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Event;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;

//@Builder
@EqualsAndHashCode
public class ViewEventDTO
{
    public int id;
    public String name;
    public String description;
    public String eventCategory;
    public Date eventDate;
    public LocationDTO eventLocationDTO;

    public ViewEventDTO()
    {

    }

    public ViewEventDTO(Event event)
    {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.eventCategory = event.getEventCategory();
        this.eventDate = event.getEventDate();
        this.eventLocationDTO = new LocationDTO(event.getEventLocation());
    }
}
