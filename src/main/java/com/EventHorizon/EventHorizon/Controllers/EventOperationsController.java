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
    public ResponseEntity<ViewEventDto> getEventForUser(@PathVariable int eventId) {
        ViewEventDto viewEventDTO = this.eventService.getEventForUser(eventId);
        return new ResponseEntity<>(viewEventDTO, HttpStatus.OK);
    }

    @GetMapping("EventForOrganizer/{organizerId}/{eventId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> getEventForOrganizer(@PathVariable int organizerId, @PathVariable int eventId) {
        DetailedEventDto detailedEventDTO = this.eventService.getEventForOrganizer(organizerId, eventId);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @PostMapping("createEvent/{organizerId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> createEvent
            (@PathVariable int organizerId, @RequestBody DetailedEventDto detailedEventDto) {
        detailedEventDto = this.eventService.createEvent(organizerId, detailedEventDto);
        return new ResponseEntity<>(detailedEventDto, HttpStatus.OK);
    }

    @PutMapping("updateEvent/{organizerId}/{eventId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> updateEvent
            (@PathVariable int organizerId, @PathVariable int eventId, @RequestBody DetailedEventDto detailedEventDTO) {
        detailedEventDTO = this.eventService.createEvent(organizerId, detailedEventDTO);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteEvent/{organizerId}/{eventId}") //organizer,admin
    public ResponseEntity deleteEvent
            (@PathVariable int organizerId, @PathVariable int eventId) {
        this.eventService.deleteEvent(organizerId, eventId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("dashboard/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getEventHeaders(@PathVariable int pageIndex, @PathVariable int pageSize) {
        List<EventHeaderDto> eventHeaders = this.eventService.getEventHeadersList(pageIndex, pageSize);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

}