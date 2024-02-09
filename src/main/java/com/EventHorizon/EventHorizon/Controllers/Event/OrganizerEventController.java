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
@RequestMapping("/organizerEvent/")
@CrossOrigin("*")
public class OrganizerEventController {

    @Autowired
    private DashboardService dashboardService;

    @PostMapping("futureLaunched/{pageIndex}/{pageSize}/{organizerId}")
    public ResponseEntity<List<EventHeaderDto>> getEventHeadersForFutureLaunched(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int organizerId, @RequestBody FilterDto filterDto) {
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters = filterDto.getFilters();
        filters.add(new FilterRelationList<>(FilterTypes.ORGANIZERID, FilterRelation.AND, organizerId));
        filters.add(new FilterRelationList<>(FilterTypes.FUTURE_DATE, FilterRelation.AND, System.currentTimeMillis()));
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filters, EventViewType.LAUNCHED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

    @PostMapping("pastLaunched/{pageIndex}/{pageSize}/{organizerId}")
    public ResponseEntity<List<EventHeaderDto>> getEventHeadersForPastLaunched(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int organizerId, @RequestBody FilterDto filterDto) {
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters = filterDto.getFilters();
        filters.add(new FilterRelationList<>(FilterTypes.ORGANIZERID, FilterRelation.AND, organizerId));
        filters.add(new FilterRelationList<>(FilterTypes.PAST_DATE, FilterRelation.AND, System.currentTimeMillis()));
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filters, EventViewType.LAUNCHED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

    @PostMapping("draftedForOrganizer/{pageIndex}/{pageSize}/{organizerId}")
    public ResponseEntity<List<EventHeaderDto>> getEventHeadersForDraftedForOrganizer(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int organizerId, @RequestBody FilterDto filterDto) {
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters = filterDto.getFilters();
        filters.add(new FilterRelationList<>(FilterTypes.ORGANIZERID, FilterRelation.AND, organizerId));
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filters, EventViewType.DRAFTED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

}
