package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class EventRepositryTest {
    @Autowired
    private EventRepositry eventRepositry;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    @Test
    public void saveEvent(){
        AdsOption adsOption =AdsOption.builder().name("p").priority(1).build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("Egypt").city("Alex").build();
        Event event=Event.builder().eventAds(adsOption).eventLocation(location1).name("e5").description("...").build();
        eventRepositry.save(event);
        Event event2=Event.builder().name("e6").description("...").eventAds(adsOption).build();
        eventRepositry.save(event2);
    }
    @Test
    public void findEvent1(){
        Optional<Event> event=eventRepositry.findById(23);
        if(event!=null){
        Event nonNullEvent=event.get();
        System.out.println(nonNullEvent.toString());}
        else System.out.println("Not found");
    }
    @Test
    public void findEvent2(){
        Optional<Event> event=eventRepositry.findById(1);
        if(event.isPresent()){
            Event nonNullEvent=event.get();
            System.out.println(nonNullEvent.toString());}
        else System.out.println("Not found");
    }


}