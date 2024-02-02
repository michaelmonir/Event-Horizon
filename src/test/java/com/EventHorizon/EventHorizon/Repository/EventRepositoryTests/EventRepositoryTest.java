package com.EventHorizon.EventHorizon.Repository.EventRepositoryTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AdsOptionRepository adsOptionRepository;
    @Autowired
    private UserRepository organizerRepository;
    @Autowired
    private UserCustomCreator userCustomCreator;

    private Event tempEvent;
    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;
    private Location tempLocation;


    @Test
    public void createNewEvent() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        eventRepository.save(tempEvent);
        Assertions.assertNotEquals(0, tempEvent.getId());

    }

    @Test
    public void testManyToOneRelationBetwenAdsAndEvent() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        eventRepository.save(tempEvent);
        Event event2 = Event.builder()
                .name("e6")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer).build();
        eventRepository.save(event2);
        Assertions.assertEquals(tempEvent.getEventAds().getId(), event2.getEventAds().getId());
    }

    @Test
    public void testOneToOneRelationBetwenLocationAndEventWithError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        eventRepository.save(tempEvent);
        Event event2 = Event.builder()
                .name("e6")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer)
                .eventLocation(tempLocation).build();
        Assertions.assertThrows(RuntimeException.class, () -> {
            eventRepository.save(event2);
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
        eventRepository.save(tempEvent);
        Event event2 = Event.builder()
                .name("e6")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer)
                .eventLocation(location2).build();

        Assertions.assertDoesNotThrow(() -> {
            eventRepository.save(event2);
            Assertions.assertNotEquals(tempEvent.getEventLocation().getId(), event2.getEventLocation().getId());
        });

    }

    @Test
    public void findNotExistedEventById() {
        Optional<Event> event = eventRepository.findById(0);
        Assertions.assertFalse(event.isPresent());
    }

    @Test
    public void findExistedEventById() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        eventRepository.save(tempEvent);
        Optional<Event> findedEvent = eventRepository.findById(tempEvent.getId());
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
            eventRepository.save(event);
        });
    }

    @Test
    public void createEventWithoutAdsOption() {
        insialize();
        Assertions.assertThrows(RuntimeException.class, () -> {
            eventRepository.save(tempEvent);
        });
    }

    private void insialize() {
        createOrganizer();
        createAdsOption();
        createLocation();
        createEvent();
    }

    private void createOrganizer() {
        tempOrganizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);
        organizerRepository.save(tempOrganizer);
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