package com.EventHorizon.EventHorizon.EventCreation.EventCreationService;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.EventService.EventService;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import com.EventHorizon.EventHorizon.Exceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class EventServiceTest {

    @Autowired
    private EventService eventService;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    @Test
    public void testGettingExceptionOnSendingIdWhenCreating()
    {
        Event event = new Event();
        event.setId(5);
        event.setName("Michael's Event");

        try {
            eventService.saveEventWhenCreating(event);
            Assertions.fail();
        }
        catch (EventAlreadyExisting e)
        {
        }
    }
    @Test
    public void addEventNotGettingError(){
        AdsOption adsOption =AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("Egypt").city("cairo").build();
        Event event=Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("e45")
                .description("...")
                .build();
        try {
            eventService.saveEventWhenCreating(event);
            Assertions.assertNotEquals(0,event.getId());

        }
        catch (EventAlreadyExisting e)
        {
        }
    }
    @Test
    public void editEventGettingErrorEventNotFoundException(){
        AdsOption adsOption =AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("Egypt").city("cairo").build();
        Event event=Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("11")
                .description("...")
                .build();
        try {
            eventService.updateEvent(0,event);
            Assertions.fail();
        }
        catch (EventNotFoundException e)
        {
        }
    }

    @Test
    public void editEventGettingErrorEventAlreadyExisting(){
        AdsOption adsOption =AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("aswan").city("cairo").build();
        Event event=Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("e45")
                .description("...")
                .id(27)
                .build();
        try {
            eventService.updateEvent(34,event);
        }
        catch (EventAlreadyExisting e)
        {
        }
    }
    @Test
    public void editEventwithoutError(){
        AdsOption adsOption =AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1= Location.builder().country("aswan").city("cairo").build();
        Event event=Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("e48")
                .description("...")
                .build();
        eventService.saveEventWhenCreating(event);
        Event newEvent=Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("e500")
                .description("newevent")
                .build();
        try {
            eventService.updateEvent(event.getId(),newEvent);
            Assertions.assertEquals(event.getId(),newEvent.getId());

        }
        catch (EventAlreadyExisting e)
        {
        }
    }

}