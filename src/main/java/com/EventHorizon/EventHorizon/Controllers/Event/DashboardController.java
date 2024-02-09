package com.EventHorizon.EventHorizon.Controllers.Event;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.FilterDto;
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
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @PostMapping("dashboard/{pageIndex}/{pageSize}")//any
    public ResponseEntity<List<EventHeaderDto>> getDashBoardLaunchedEvents(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<EventHeaderDto> eventHeaders = dashboardService.getFilteredPage(pageIndex, pageSize, filterDto.getFilters(), EventViewType.LAUNCHED);
        return new ResponseEntity<>(eventHeaders, HttpStatus.OK);
    }

}
