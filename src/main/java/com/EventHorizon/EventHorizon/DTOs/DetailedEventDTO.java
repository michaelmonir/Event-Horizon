package com.EventHorizon.EventHorizon.DTOs;

import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

//@Builder
@EqualsAndHashCode
public class DetailedEventDTO
{
    public int id;
    public String name;
    public String description;
    public String eventCategory;
    public Date eventDate;
    public AdsOptionDTO eventAds;
    public LocationDTO eventLocation;

    public DetailedEventDTO(Event event)
    {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.eventCategory = event.getEventCategory();
        this.eventDate = event.getEventDate();

        this.eventLocation = new LocationDTO(event.getEventLocation());
        this.eventAds = new AdsOptionDTO(event.getEventAds());
    }

    public Event getEvent()
    {
        Event event = Event.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .eventCategory(this.eventCategory)
                .eventDate(this.eventDate)
                .eventAds(this.eventAds.getAdsOption())
                .eventLocation(this.eventLocation.getLocation())
                .build();
        return event;
    }
}
