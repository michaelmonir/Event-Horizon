package com.EventHorizon.EventHorizon.EventCreation.EventService;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LocationServiceTest {
    @Autowired
    private EventService eventService;
    @Autowired
    private LocationService locationService;
    @Test
    public void testDeletionOfLocationByEcventId(){
        Location location= Location.builder()
                .country("mozmbeq")
                .city("Alex").build();
        Event event=Event.builder()
                .name("micol")
                .eventLocation(location)
                .description("neo").build();
         eventService.saveEventWhenCreating(event);
        locationService.deleteLocationById(event.getId());
    }

}