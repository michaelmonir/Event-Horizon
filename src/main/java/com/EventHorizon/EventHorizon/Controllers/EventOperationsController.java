package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event/")
@CrossOrigin("*")
public class EventOperationsController {
    @Autowired
    private EventService eventService;

    @GetMapping("eventForUser/{eventId}")//any
    public ApiResponse getEventForUser(@PathVariable int eventId) {
        ViewEventDto viewEventDTO=this.eventService.getEventForUser(eventId);
        return new ApiResponse<>( "Found Successfully",viewEventDTO);
    }
    @GetMapping("EventForOrganizer/{organizerId}/{eventId}")//organizer,admin
    public ApiResponse getEventForOrganizer(@PathVariable int organizerId, @PathVariable int eventId) {
        DetailedEventDto detailedEventDTO=this.eventService.getEventForOrganizer(organizerId, eventId);
        return new ApiResponse<>( "Found Successfully",detailedEventDTO);
    }

//    @PostMapping("createEvent")//organizer,admin
//    public ApiResponse createEvent(@RequestBody DetailedEventDto detailedEventDTO) {
//        int organizerId = 1;
//        System.out.println("event");
//        System.out.println("event");
//        System.out.println("event");
//        detailedEventDTO = this.eventService.createEvent(organizerId, detailedEventDTO);
//        return new ApiResponse<>( "Created Successfully",detailedEventDTO);
//    }

    @PostMapping("createEvent/{organizerId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> createEvent(@PathVariable int organizerId, @RequestBody DetailedEventDto detailedEventDto) {
        System.out.println(detailedEventDto);
        System.out.println("event");
        System.out.println("event");
        System.out.println("event");
        detailedEventDto = this.eventService.createEvent(organizerId, detailedEventDto);
//        return new ApiResponse<>( "Created Successfully",detailedEventDto);
        return new ResponseEntity<>(detailedEventDto, HttpStatus.OK);
    }

    @GetMapping("")//organizer,admin
    public ApiResponse<Integer> donothin()
    {
        System.out.println("m");
        System.out.println("m");
        System.out.println("m");
        return new ApiResponse<>(HttpStatus.NOT_FOUND, "", 5);
    }
    @PutMapping("updateEvent/{organizerId}/{eventId}")//organizer,admin
    public ApiResponse updateEvent
            (@PathVariable int organizerId, @PathVariable int eventId, @RequestBody DetailedEventDto detailedEventDTO) {
        detailedEventDTO = this.eventService.createEvent(organizerId, detailedEventDTO);
        return new ApiResponse<>( "Updated Successfully",detailedEventDTO);
    }
    @DeleteMapping("deleteEvent/{organizerId}/{eventId}") //organizer,admin
    public ApiResponse deleteEvent
            (@PathVariable int organizerId, @PathVariable int eventId) {
        this.eventService.deleteEvent(organizerId,eventId);
        return new ApiResponse<>( "Deleted Successfully");
    }
    @GetMapping("EventHeaders/{pageIndex}/{pageSize}")//any
    public ApiResponse getEventHeaders(@PathVariable int pageIndex, @PathVariable int pageSize) {
        List<EventHeaderDto> eventHeaders =this.eventService.getEventHeadersList(pageIndex,pageSize);
        return new ApiResponse<>( "Found Successfully",eventHeaders);
    }

}