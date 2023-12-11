package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class EventRepositryTest {
    @Autowired
    private EventRepositry eventRepositry;
    @Autowired
    private AdsOptionRepository adsOptionRepository;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private InformationCreator informationCreator;

    private Event tempEvent;
    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;
    private Location tempLocation;


    @Test
    public void createNewEvent() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        eventRepositry.save(tempEvent);
        Assertions.assertNotEquals(0, tempEvent.getId());

    }

    @Test
    public void testManyToOneRelationBetwenAdsAndEvent() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        eventRepositry.save(tempEvent);
        Event event2 = Event.builder()
                .name("e6")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer).build();
        eventRepositry.save(event2);
        Assertions.assertEquals(tempEvent.getEventAds().getId(), event2.getEventAds().getId());
    }

    @Test
    public void testOneToOneRelationBetwenLocationAndEventWithError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        eventRepositry.save(tempEvent);
        Event event2 = Event.builder()
                .name("e6")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer)
                .eventLocation(tempLocation).build();
        Assertions.assertThrows(RuntimeException.class, () -> {
            eventRepositry.save(event2);
        });
    }

    @Test
    public void testOneToOneRelationBetwenLocationAndEventWithoutError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        Location location2 = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        eventRepositry.save(tempEvent);
        Event event2 = Event.builder()
                .name("e6")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer)
                .eventLocation(location2).build();

        Assertions.assertDoesNotThrow(() -> {
            eventRepositry.save(event2);
            Assertions.assertNotEquals(tempEvent.getEventLocation().getId(), event2.getEventLocation().getId());
        });

    }

    @Test
    public void findNotExistedEventById() {
        Optional<Event> event = eventRepositry.findById(0);
        Assertions.assertFalse(event.isPresent());
    }

    @Test
    public void findExistedEventById() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        eventRepositry.save(tempEvent);
        Optional<Event> findedEvent = eventRepositry.findById(tempEvent.getId());
        Assertions.assertTrue(findedEvent.isPresent());
    }

    @Test
    public void createEventWithoutName() {
        insialize();
        Event event = Event.builder()
                .eventAds(tempAdsOption)
                .eventLocation(tempLocation)
                .eventOrganizer(tempOrganizer)
                .description("...").build();

        Assertions.assertThrows(RuntimeException.class, () -> {
            eventRepositry.save(event);
        });
    }

    @Test
    public void createEventWithoutAdsOption() {
        insialize();
        Assertions.assertThrows(RuntimeException.class, () -> {
            eventRepositry.save(tempEvent);
        });
    }

    private void insialize() {
        createOrganizer();
        createAdsOption();
        createLocation();
        createEvent();
    }

    private void createOrganizer() {
        Information information = informationCreator.getInformation(Role.ORGANIZER);
        Organizer organizer = Organizer.builder().information(information).build();
        organizerRepository.save(organizer);
        tempOrganizer = organizer;
    }

    public void createAdsOption() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepository.save(adsOption);
        tempAdsOption = adsOption;

    }

    private void createEvent() {
        tempEvent = Event.builder()
                .name("e5")
                .eventOrganizer(tempOrganizer)
                .description("...").build();
    }

    private void createLocation() {
        tempLocation = Location.builder()
                .country("Egypt")
                .city("Alex").build();
    }

}