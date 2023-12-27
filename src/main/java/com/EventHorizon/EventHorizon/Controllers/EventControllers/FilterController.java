package com.EventHorizon.EventHorizon.Controllers.EventControllers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.FilterDto;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterRelation;
import com.EventHorizon.EventHorizon.Filter.Enums.FilterTypes;
import com.EventHorizon.EventHorizon.Filter.FilterRelationList;
import com.EventHorizon.EventHorizon.Services.EventServices.EventService;
import com.EventHorizon.EventHorizon.Services.EventServices.FilterService;
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
    private EventService eventService;
    @Autowired
    private FilterService filterService;

    /*
     * for launched
     */
    @PostMapping("dashboard/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getEventHeaders(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters = filterDto.getFilters();
        filters.add(new FilterRelationList<>(FilterTypes.EVENTTYPE, FilterRelation.AND, EventType.LAUNCHEDEVENT));
        List<EventHeaderDto> eventHeaders = this.filterService.getFilteredEventHeadersList(pageIndex, pageSize, filterDto.getFilters());
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }
    /*
     * for pending
     */
    @PostMapping("drafted/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getEventHeadersForPending(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<FilterRelationList<FilterTypes, FilterRelation, Object>> filters = filterDto.getFilters();
        filters.add(new FilterRelationList<>(FilterTypes.EVENTTYPE, FilterRelation.AND, EventType.DRAFTEDEVENT));
        List<EventHeaderDto> eventHeaders = this.filterService.getFilteredEventHeadersList(pageIndex, pageSize, filters);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

}
