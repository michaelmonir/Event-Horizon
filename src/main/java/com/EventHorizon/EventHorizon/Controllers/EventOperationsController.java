package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.DetailedDraftedEventDto;
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

    @GetMapping("eventForUser/{eventId}")//any
    public ResponseEntity<ViewEventDto> getEventForUser(@PathVariable int eventId) {
        ViewEventDto viewEventDTO = this.eventService.getEventForUser(eventId);
        return new ResponseEntity<>(viewEventDTO, HttpStatus.OK);
    }

    @GetMapping("LaunchedEventForOrganizer/{eventId}")//organizer,admin
    public ResponseEntity<DetailedLaunchedEventDto> getLaunchedEventForOrganizer(HttpServletRequest request, @PathVariable int eventId) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        DetailedLaunchedEventDto detailedLaunchedEventDTO = this.eventService.getLaunchedEventForOrganizer(organizerId, eventId);
        return new ResponseEntity<>(detailedLaunchedEventDTO, HttpStatus.OK);
    }
    @GetMapping("DraftedEventForOrganizer/{eventId}")//organizer,admin
    public ResponseEntity<DetailedDraftedEventDto> getDraftedEventForOrganizer(HttpServletRequest request, @PathVariable int eventId) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        DetailedDraftedEventDto detailedDraftedEventDTO = this.eventService.getDraftedEventForOrganizer(organizerId, eventId);
        return new ResponseEntity<>(detailedDraftedEventDTO, HttpStatus.OK);
    }


    @PostMapping("createLaunchedEvent")//organizer,admin
    public ResponseEntity<DetailedLaunchedEventDto> createLaunchedEvent
            (HttpServletRequest request, @RequestBody DetailedLaunchedEventDto detailedLaunchedEventDto) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        detailedLaunchedEventDto = this.eventService.createLaunchedEvent(organizerId, detailedLaunchedEventDto);
        return new ResponseEntity<>(detailedLaunchedEventDto, HttpStatus.OK);
    }

    @PostMapping("createDraftedEvent")//organizer,admin
    public ResponseEntity<DetailedDraftedEventDto> createDraftedEvent
            (HttpServletRequest request, @RequestBody DetailedDraftedEventDto detailedDraftedEventDto) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        detailedDraftedEventDto = this.eventService.createDraftedEvent(organizerId, detailedDraftedEventDto);
        return new ResponseEntity<>(detailedDraftedEventDto, HttpStatus.OK);
    }

    @PutMapping("updateLaunchedEvent/{eventId}")//organizer,admin
    public ResponseEntity<DetailedLaunchedEventDto> updateLaunchedEvent
            (HttpServletRequest request, @PathVariable int eventId, @RequestBody DetailedLaunchedEventDto detailedLaunchedEventDTO) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        detailedLaunchedEventDTO = this.eventService.createLaunchedEvent(organizerId, detailedLaunchedEventDTO);
        return new ResponseEntity<>(detailedLaunchedEventDTO, HttpStatus.OK);
    }
    @PutMapping("updateDraftedEvent/{eventId}")//organizer,admin
    public ResponseEntity<DetailedDraftedEventDto> updateDraftedEvent
            (HttpServletRequest request, @PathVariable int eventId, @RequestBody DetailedDraftedEventDto detailedDraftedEventDTO) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        detailedDraftedEventDTO = this.eventService.createDraftedEvent(organizerId, detailedDraftedEventDTO);
        return new ResponseEntity<>(detailedDraftedEventDTO, HttpStatus.OK);
    }

    @DeleteMapping("deleteLaunchedEvent/{eventId}") //organizer,admin
    public ResponseEntity deleteLaunchedEvent
            (HttpServletRequest request, @PathVariable int eventId) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        this.eventService.deleteLaunchedEvent(organizerId, eventId);
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping("deleteDraftedEvent/{eventId}") //organizer,admin
    public ResponseEntity deleteDraftedEvent
            (HttpServletRequest request, @PathVariable int eventId) {

        int organizerId = this.userTokenInformationService.getUserIdFromToken(request);
        this.eventService.deleteDraftedEvent(organizerId, eventId);
        return new ResponseEntity(HttpStatus.OK);
    }

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