package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EventRepositryTest {
    @Autowired
    private EventRepositry eventRepositry;
    @Test
    public void saveEvent(){
        AdsOption adsOption =AdsOption.builder().name("p").priority(1).build();
        Location location1= Location.builder().country("Egypt").city("Alex").build();
        Event event=Event.builder().eventAds(adsOption).eventLocation(location1).name("e1").description("...").build();
        eventRepositry.save(event);
    }


}