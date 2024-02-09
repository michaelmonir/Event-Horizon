package com.EventHorizon.EventHorizon.Controllers.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.*;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.LaunchedEventRepositoryServiceImpl;
import com.EventHorizon.EventHorizon.Services.Event.EventService;
import com.EventHorizon.EventHorizon.Services.Event.EventView.EventViewService;
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
    private EventViewService eventViewService;
    @Autowired
    LaunchedEventRepositoryServiceImpl launchedEventRepositoryServiceImpl;

    @GetMapping("eventForUser/{eventId}/{userInformationId}") //any
    public ResponseEntity<EventViewDto> getEvent(@PathVariable int eventId,@PathVariable int userInformationId) {

        EventViewDto detailedEventDTO = this.eventViewService.getEventViewDto(eventId, userInformationId);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @PostMapping("createEvent/{organizerId}")//organizer,admin
    public ResponseEntity<EventViewDto> createEvent
            (@PathVariable int organizerId, @RequestBody EventCreationUpdationDto creationDto) {
        EventViewDto detailedEventDto = eventService.createEvent(organizerId, creationDto);
        return new ResponseEntity<>(detailedEventDto, HttpStatus.OK);
    }

    @PutMapping("updateEvent/{organizerInformationId}")//organizer,admin
    public ResponseEntity<EventViewDto> updateEvent
            (@PathVariable int organizerId,@RequestBody EventCreationUpdationDto eventUpdationDto) {
        EventViewDto detailedEventDTO = this.eventService.updateEvent(organizerId, eventUpdationDto);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @PutMapping("launchEvent/{organizerId}/{eventId}")//organizer,admin
    public ResponseEntity<EventViewDto> launchEvent
            (HttpServletRequest request, @PathVariable int organizerId, @PathVariable int eventId) {
        EventViewDto detailedEventDTO = this.eventService.launchEvent(organizerId, eventId);
        return new ResponseEntity<>(detailedEventDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteEvent/{eventId}") //organizer,admin
    public ResponseEntity deleteEvent
            (HttpServletRequest request, @PathVariable int eventId) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        this.eventService.deleteEvent(organizerId, eventId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getSeatType/{id}")//any
    public ResponseEntity<List<SeatType>> getEventHeaders(@PathVariable int eventId) {
        List<SeatType> list = this.launchedEventRepositoryServiceImpl.getSeatTypeById(eventId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}