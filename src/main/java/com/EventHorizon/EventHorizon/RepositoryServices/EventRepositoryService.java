package com.EventHorizon.EventHorizon.RepositoryServices;

import com.EventHorizon.EventHorizon.DTOs.DetailedEventDTO;
import com.EventHorizon.EventHorizon.DTOs.LocationDTO;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class EventRepositoryService {
    @Autowired
    private EventRepositry eventRepositry;
    @Autowired
    private AdsOptionRepositoryService adsOptionRepositoryService;

    public Event getEventAndHandleNotFound(int id) {
        Optional<Event> optionalOldEvent=eventRepositry.findById(id);
        if(!optionalOldEvent.isPresent())
            throw new EventNotFoundException();
        return optionalOldEvent.get();
    }

    public Event saveEventWhenCreatingAndHandleAlreadyExisting(Event event){
        if (event.getId() != 0) throw new EventAlreadyExisting();

        eventRepositry.save(event);
        return event;
    }

    public Event updateEventAndHandleNotFound(int id, Event newEvent){
        this.getEventAndHandleNotFound(id);

        newEvent.setId(id);
        eventRepositry.save(newEvent);
        return newEvent;
    }



    public void mm()
    {
        int z = 0;
        z++;
        z += 5;
        int x = z+5;
    }


    public DetailedEventDTO getDTOfromDetailedEvent(Event event)
    {
        DetailedEventDTO dto = new DetailedEventDTO();
        dto.id = event.getId();
        dto.name = event.getName();
        dto.description = event.getDescription();
        dto.eventCategory = event.getEventCategory();
        dto.eventDate = event.getEventDate();

        dto.eventLocation = new LocationDTO(event.getEventLocation());
        dto.eventAds = this.adsOptionRepositoryService.getDTOFromAdsOption(event.getEventAds());
        return dto;
    }

    public Event getEventFromDetailedEventDTO(DetailedEventDTO dto)
    {
        Event event = Event.builder()
                .id(dto.id)
                .name(dto.name)
                .description(dto.description)
                .eventCategory(dto.eventCategory)
                .eventDate(dto.eventDate)
                .eventAds(this.adsOptionRepositoryService.getAdsOptionFromDTO(dto.eventAds))
                .eventLocation(dto.eventLocation.getLocation())
                .build();
        return event;
    }
}

