package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventOperationsController {
    @Autowired
    private EventService eventService;

    @GetMapping("/{eventId}")
    public ApiResponse getEventForUser(@PathVariable int eventId) {
        ViewEventDto viewEventDTO=this.eventService.getEventForUser(eventId);
        return new ApiResponse<>( "Found Successfully",viewEventDTO);
    }

    @GetMapping("/{organizerId}/{eventId}")
    public ApiResponse getEventForOrganizer(@PathVariable int organizerId, @PathVariable int eventId) {
        DetailedEventDto detailedEventDTO=this.eventService.getEventForOrganizer(organizerId, eventId);
        return new ApiResponse<>( "Found Successfully",detailedEventDTO);
    }

    @PostMapping("/{organizerId}")
    public ApiResponse createEvent(@PathVariable int organizerId, @RequestBody DetailedEventDto detailedEventDTO) {
        detailedEventDTO = this.eventService.createEvent(organizerId, detailedEventDTO);
        return new ApiResponse<>( "Created Successfully",detailedEventDTO);
    }

    @PutMapping("/{organizerId}/{eventId}")
    public ApiResponse updateEvent
            (@PathVariable int organizerId, @PathVariable int eventId, @RequestBody DetailedEventDto detailedEventDTO) {
        detailedEventDTO = this.eventService.createEvent(organizerId, detailedEventDTO);
        return new ApiResponse<>( "Updated Successfully",detailedEventDTO);
    }

    @DeleteMapping("/{organizerId}/{eventId}")
    public ApiResponse deleteEvent
            (@PathVariable int organizerId, @PathVariable int eventId) {
        this.eventService.deleteEvent(organizerId,eventId);
        return new ApiResponse<>( "Deleted Successfully");
    }

    @GetMapping("/{pageIndex}/{pageSize}")
    public ApiResponse getEventHeaders(@PathVariable int pageIndex, @PathVariable int pageSize) {
        List<EventHeaderDto> eventHeaders =this.eventService.getEventHeadersList(pageIndex,pageSize);
        return new ApiResponse<>( "Found Successfully",eventHeaders);
    }

}