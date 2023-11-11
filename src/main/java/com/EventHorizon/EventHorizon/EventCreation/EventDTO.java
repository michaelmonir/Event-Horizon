package com.EventHorizon.EventHorizon.EventCreation;

import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class EventDTO
{
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;
    @Autowired
    private LocationRepository locationRepository;

    private String name;
    private String description;
    private String eventCategory;
    private Date eventDate;
    private int eventAdsId;
    private LocationDTO eventLocation;

    public Event getEvent()
    {
        Event event = Event.builder()
                .name(name)
                .description(description)
                .eventCategory(eventCategory)
                .eventDate(eventDate)
                .eventAds(adsOptionRepositry.findById(eventAdsId).get())
                .eventLocation(eventLocation.getLocation())
                .build();
        return event;
    }
}
