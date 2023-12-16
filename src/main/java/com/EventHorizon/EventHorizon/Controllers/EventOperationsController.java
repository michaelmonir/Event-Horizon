package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventService;
import com.EventHorizon.EventHorizon.Services.UserTokenInformationService;
import com.EventHorizon.EventHorizon.security.Service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
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
    @Autowired
    private UserTokenInformationService userTokenInformationService;

    @Autowired
    LaunchedEventRepositoryService launchedEventRepositoryService;

    @GetMapping("eventForUser/{eventId}")//any
    public ResponseEntity<ViewEventDto> getEventForUser(@PathVariable int eventId) {
        ViewEventDto viewEventDTO = this.eventService.getEventForUser(eventId);
        return new ResponseEntity<>(viewEventDTO, HttpStatus.OK);
    }

    @GetMapping("EventForOrganizer/{eventId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> getEventForOrganizer(HttpServletRequest request, @PathVariable int eventId,@RequestBody EventType eventType) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        DetailedEventDto detailedEventDTO = this.eventService.getEventForOrganizer(organizerId, eventId,eventType);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @PostMapping("createEvent/{organizerId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> createEvent
            (@PathVariable int organizerId, @RequestBody DetailedLaunchedEventDto detailedEventDto) {

//        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        detailedEventDto = (DetailedLaunchedEventDto) eventService.createEvent(organizerId, detailedEventDto);
        return new ResponseEntity<>(detailedEventDto, HttpStatus.OK);
    }

    @PutMapping("updateEvent/{organizerId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> updateEvent
            (@PathVariable int organizerId,@RequestBody DetailedLaunchedEventDto detailedEventDTO) {

      //  int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        detailedEventDTO = (DetailedLaunchedEventDto) this.eventService.updateEvent(organizerId, detailedEventDTO);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }
    @PutMapping("launchEvent/{eventId}")//organizer,admin
    public ResponseEntity<DetailedEventDto> launchEvent
            (HttpServletRequest request, @PathVariable int eventId, @RequestBody DetailedEventDto detailedEventDTO) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        detailedEventDTO = this.eventService.launchEvent(organizerId, detailedEventDTO);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteEvent/{eventId}") //organizer,admin
    public ResponseEntity deleteEvent
            (HttpServletRequest request, @PathVariable int eventId,@RequestBody DetailedEventDto detailedEventDTO) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        this.eventService.deleteEvent(organizerId, detailedEventDTO);
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