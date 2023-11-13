package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.DetailedEventDTO;
import com.EventHorizon.EventHorizon.DTOs.ViewEventDTO;
import com.EventHorizon.EventHorizon.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventOperationsController
{
    @Autowired
    private EventService eventService;

    @GetMapping("/{eventId}")
    public ViewEventDTO getEventForUser(@PathVariable int eventId)
    {
        return this.eventService.getEventForUser(eventId);
    }

    @GetMapping("/{organizerId}/{eventId}")
    public DetailedEventDTO getEventForUser(@PathVariable int organizerId, @PathVariable int eventId)
    {
        return this.eventService.getEventForOrganizer(organizerId, eventId);
    }


    @PostMapping("/{organizerId}")
    public DetailedEventDTO createEvent(@PathVariable int organizerId, @RequestBody DetailedEventDTO detailedEventDTO)
    {
        return this.eventService.createEvent(organizerId, detailedEventDTO);
    }

    @PutMapping("/{organizerId}/{eventId}")
    public DetailedEventDTO updateEvent
            (@PathVariable int organizerId, @PathVariable int eventId, @RequestBody DetailedEventDTO detailedEventDTO)
    {
        return this.eventService.updateEvent(organizerId, eventId, detailedEventDTO);
    }

    // should put getListOfEvents but dashboard not implemented yet
}