package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.Event;
import com.EventHorizon.EventHorizon.Entities.Location;

import com.EventHorizon.EventHorizon.Exceptions.EventAlreadyExisting;
import org.hibernate.PersistentObjectException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class EventRepositryTest {
    @Autowired
    private EventRepositry eventRepositry;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;


    @Test
    public void createEvent() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .name("e5")
                .eventLocation(location)
                .description("...").build();
        eventRepositry.save(event);
        Assertions.assertNotEquals(0, event.getId());

    }

    @Test
    public void testManyToOneRelationBetwenAdsAndEvent() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepositry.save(adsOption);
        Event event = Event.builder()
                .eventAds(adsOption)
                .name("e5")
                .description("...").build();
        eventRepositry.save(event);
        Event event2 = Event.builder()
                .name("e6")
                .description("...")
                .eventAds(adsOption).build();
        eventRepositry.save(event2);
        Assertions.assertEquals(event.getEventAds().getId(), event2.getEventAds().getId());
    }

    @Test
    public void testOneToOneRelationBetwenLocationAndEventWithError() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        Event event = Event.builder()
                .eventLocation(location)
                .name("e5")
                .eventAds(adsOption)
                .description("...").build();
        eventRepositry.save(event);
        Event event2 = Event.builder()
                .name("e6")
                .description("...")
                .eventAds(adsOption)
                .eventLocation(location).build();
        Assertions.assertThrows(RuntimeException.class, () -> {
            eventRepositry.save(event2);
        });


    }

    @Test
    public void testOneToOneRelationBetwenLocationAndEventWithoutError() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1 = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        Location location2 = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        Event event = Event.builder()
                .eventLocation(location1)
                .name("e5")
                .eventAds(adsOption)
                .description("...").build();
        eventRepositry.save(event);
        Event event2 = Event.builder()
                .name("e6")
                .description("...")
                .eventAds(adsOption)
                .eventLocation(location2).build();

        Assertions.assertDoesNotThrow(() -> {
            eventRepositry.save(event2);
            Assertions.assertNotEquals(event.getEventLocation().getId(), event2.getEventLocation().getId());
        });

    }

    @Test
    public void findNotExistedEventById() {
        Optional<Event> event = eventRepositry.findById(0);
        Assertions.assertEquals(event.isPresent(), false);
    }

    @Test
    public void findExistedEventById() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepositry.save(adsOption);
        Event event = Event.builder()
                .name("e5")
                .eventAds(adsOption)
                .description("...").build();
        eventRepositry.save(event);
        Optional<Event> findedEvent = eventRepositry.findById(event.getId());
        Assertions.assertEquals(findedEvent.isPresent(), true);
    }

    @Test
    public void createEventWithoutName() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location)
                .description("...").build();

        Assertions.assertThrows(RuntimeException.class, () -> {
            eventRepositry.save(event);
        });
    }

    @Test
    public void createEventWithoutAdsOption() {

        Location location = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        Event event = Event.builder()
                .name("a")
                .eventLocation(location)
                .description("...").build();

        Assertions.assertThrows(RuntimeException.class, () -> {
            eventRepositry.save(event);
        });
    }


}