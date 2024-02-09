package com.EventHorizon.EventHorizon.Controllers.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.FilterDto;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Filter.FilterRelationList;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.Filter.EventViewType;
import com.EventHorizon.EventHorizon.Services.Event.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter/")
@CrossOrigin("*")
public class FilterController {

    @Autowired
    private DashboardService dashboardService;

    @PostMapping("dashboard/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getDashBoardLaunchedEvents(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filterDto.getFilters(), EventViewType.LAUNCHED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

    @PostMapping("drafted/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getDashBoardDraftedEvents(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filterDto.getFilters(), EventViewType.DRAFTED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

    @PostMapping("draftedForOrganizer/{pageIndex}/{pageSize}/{organizerId}")//any
    public ResponseEntity<List<EventHeaderDto>> getEventHeadersForDraftedForOrganizer(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int organizerId, @RequestBody FilterDto filterDto) {
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters = filterDto.getFilters();
        filters.add(new FilterRelationList<>(FilterTypes.ORGANIZERID, FilterRelation.AND, organizerId));
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filters, EventViewType.DRAFTED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }
}
