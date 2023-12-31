package com.EventHorizon.EventHorizon.Controllers.EventControllers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventServices.EventService;
import com.EventHorizon.EventHorizon.Services.UserServices.UserTokenInformationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event/")
@CrossOrigin("*")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserTokenInformationService userTokenInformationService;

    @Autowired
    LaunchedEventRepositoryService launchedEventRepositoryService;

    @GetMapping("eventForUser/{eventId}")//any
    public ResponseEntity<DetailedEventDto> getEventForUser(@PathVariable int eventId) {
        DetailedEventDto detailedEventDto = this.eventService.getEventForUser(eventId);
        return new ResponseEntity<>(detailedEventDto, HttpStatus.OK);
    }

    @GetMapping("EventForOrganizer/{eventId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> getEventForOrganizer(HttpServletRequest request, @PathVariable int eventId,@RequestBody EventType eventType) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        DetailedEventDto detailedEventDTO = this.eventService.getEventForOrganizer(organizerId, eventId,eventType);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @PostMapping("createEvent/{organizerInformationId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> createEvent
            (@PathVariable int organizerInformationId, @RequestBody EventCreationUpdationDto creationDto) {

        DetailedEventDto detailedEventDto = eventService.createEvent(organizerInformationId, creationDto);
        return new ResponseEntity<>(detailedEventDto, HttpStatus.OK);
    }

    @PutMapping("updateEvent/{organizerInformationId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> updateEvent
            (@PathVariable int organizerInformationId,@RequestBody EventCreationUpdationDto eventUpdationDto) {

        DetailedEventDto detailedEventDTO = this.eventService.updateEvent(organizerInformationId, eventUpdationDto);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @PutMapping("launchEvent/{organizerId}/{eventId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> launchEvent
            (HttpServletRequest request, @PathVariable int organizerId, @PathVariable int eventId) {

//        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        DetailedEventDto detailedEventDTO = this.eventService.launchEvent(organizerId, eventId);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteEvent/{eventId}/{eventType}") //organizer,admin
    public ResponseEntity deleteEvent
            (HttpServletRequest request, @PathVariable int eventId, @PathVariable EventType eventType) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        this.eventService.deleteEvent(organizerId, eventId, eventType);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("dashboard/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getEventHeaders(@PathVariable int pageIndex, @PathVariable int pageSize) {
        List<EventHeaderDto> eventHeaders = this.eventService.getEventHeadersList(pageIndex, pageSize);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

    @GetMapping("getSeatType/{id}")//any
    public ResponseEntity<List<SeatType>> getEventHeaders(@PathVariable int eventId) {
        List<SeatType> list = this.launchedEventRepositoryService.getSeatTypeById(eventId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}