package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.FilterDto;
import com.EventHorizon.EventHorizon.Services.EventService;
import com.EventHorizon.EventHorizon.Services.FilterService;
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


    @GetMapping("dashboard/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getEventHeaders(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<EventHeaderDto> eventHeaders = this.filterService.getFilteredEventHeadersList(pageIndex, pageSize, filterDto.getFilters());
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }
}
