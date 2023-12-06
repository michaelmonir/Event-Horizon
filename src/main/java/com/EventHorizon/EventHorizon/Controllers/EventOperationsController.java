package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedLaunchedEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Services.EventService;
import com.EventHorizon.EventHorizon.Services.UserTokenInformationService;
import jakarta.servlet.http.HttpServletRequest;
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

//    @GetMapping("eventForUser/{eventId}")//any
//    public ResponseEntity<ViewEventDto> getEventForUser(@PathVariable int eventId) {
//        ViewEventDto viewEventDTO = this.eventService.getEventForUser(eventId);
//        return new ResponseEntity<>(viewEventDTO, HttpStatus.OK);
//    }

//    @GetMapping("EventForOrganizer/{eventId}")//organizer,admin
//    public ResponseEntity<DetailedLaunchedEventDto> getEventForOrganizer(HttpServletRequest request, @PathVariable int eventId) {
//
//        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
//        DetailedLaunchedEventDto detailedLaunchedEventDTO = this.eventService.getEventForOrganizer(organizerId, eventId);
//        return new ResponseEntity<>(detailedLaunchedEventDTO, HttpStatus.OK);
//    }

//    @PostMapping("createEvent")//organizer,admin
//    public ResponseEntity<DetailedLaunchedEventDto> createEvent
//            (HttpServletRequest request, @RequestBody DetailedLaunchedEventDto detailedLaunchedEventDto) {
//
//        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
//        detailedLaunchedEventDto = this.eventService.createEvent(organizerId, detailedLaunchedEventDto);
//        return new ResponseEntity<>(detailedLaunchedEventDto, HttpStatus.OK);
//    }
//
//    @PutMapping("updateEvent/{eventId}")//organizer,admin
//    public ResponseEntity<DetailedLaunchedEventDto> updateEvent
//            (HttpServletRequest request, @PathVariable int eventId, @RequestBody DetailedLaunchedEventDto detailedLaunchedEventDTO) {
//
//        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
//        detailedLaunchedEventDTO = this.eventService.createEvent(organizerId, detailedLaunchedEventDTO);
//        return new ResponseEntity<>(detailedLaunchedEventDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping("deleteEvent/{eventId}") //organizer,admin
//    public ResponseEntity deleteEvent
//            (HttpServletRequest request, @PathVariable int eventId) {
//
//        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
//        this.eventService.deleteEvent(organizerId, eventId);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @GetMapping("dashboard/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getEventHeaders(@PathVariable int pageIndex, @PathVariable int pageSize) {
        List<EventHeaderDto> eventHeaders = this.eventService.getEventHeadersList(pageIndex, pageSize);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }
    @GetMapping("test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}