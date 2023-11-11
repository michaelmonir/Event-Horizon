package com.EventHorizon.EventHorizon.EventCreation.EventCreationService;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventServiceTest {
    @Autowired
    private EventService eventService;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;
    @Test
    public void addEvent(){
        AdsOption adsOption =AdsOption.builder().name("p").priority(2).build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("Egypt").city("cairo").build();
        Event event=Event.builder().eventAds(adsOption).eventLocation(location1).name("e45").description("...").build();
        eventService.saveEvent(event);
    }
    @Test
    public void editEvent(){
        AdsOption adsOption =AdsOption.builder().name("p").priority(2).build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("Egypt").city("cairo").build();
        Event event=Event.builder().eventAds(adsOption).eventLocation(location1).name("11").description("...").build();
        eventService.updateEvent(24,event);
    }

    @Test
    public void editEvent2(){
        AdsOption adsOption =AdsOption.builder().name("p").priority(2).build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("aswan").city("cairo").build();
        Event event=Event.builder().eventAds(adsOption).eventLocation(location1).name("e45").description("...").id(27).build();
        eventService.saveEvent(event);

    }
    @Test
    public void editEvent3(){
        AdsOption adsOption =AdsOption.builder().name("p").priority(2).build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("Egypt").city("cairo").build();
        Event event=Event.builder().eventAds(adsOption).eventLocation(location1).name("11").description("...").build();
        eventService.updateEvent(30,event);
    }

}