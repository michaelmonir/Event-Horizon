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
@RequestMapping("/clientEvent/")
@CrossOrigin("*")
public class ClientEventController {

    @Autowired
    private DashboardService dashboardService;

    @PostMapping("futureEvent/{pageIndex}/{pageSize}/{clientId}")
    public ResponseEntity<List<EventHeaderDto>> getEventHeadersForFuture(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int clientId, @RequestBody FilterDto filterDto) {
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters = filterDto.getFilters();
        filters.add(new FilterRelationList<>(FilterTypes.CLIENTID, FilterRelation.AND, clientId));
        filters.add(new FilterRelationList<>(FilterTypes.FUTURE_DATE, FilterRelation.AND, System.currentTimeMillis()));
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filters, EventViewType.LAUNCHED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

    @PostMapping("pastEvent/{pageIndex}/{pageSize}/{clientId}")
    public ResponseEntity<List<EventHeaderDto>> getEventHeadersForPast(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int clientId, @RequestBody FilterDto filterDto) {
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters = filterDto.getFilters();
        filters.add(new FilterRelationList<>(FilterTypes.CLIENTID, FilterRelation.AND, clientId));
        filters.add(new FilterRelationList<>(FilterTypes.PAST_DATE, FilterRelation.AND, System.currentTimeMillis()));
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filters, EventViewType.LAUNCHED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }
}
