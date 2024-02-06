package com.EventHorizon.EventHorizon.RepositoryServices.Event;

import com.EventHorizon.EventHorizon.Entities.Event.*;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.Event.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.DraftedEventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class DraftedEventRepositoryServiceTest {
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;
    @Autowired
    private AdsOptionRepository adsOptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserCustomCreator userCustomCreator;

    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;
    private Location tempLocation;
    private DraftedEvent tempDraftedEvent;

    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        DraftedEvent event = new DraftedEvent();
        event.setId(5);
        Assertions.assertThrows(EventAlreadyExisting.class, () -> {
            draftedEventRepositoryService.saveWhenCreating(event);
        });
    }

    @Test
    public void addEventNotGettingError() {
        initialize();
        tempDraftedEvent.setEventAds(tempAdsOption);
        tempDraftedEvent.setEventLocation(tempLocation);
        Assertions.assertDoesNotThrow(() -> {
            draftedEventRepositoryService.saveWhenCreating(tempDraftedEvent);
            Assertions.assertNotEquals(0, tempDraftedEvent.getId());
        });
    }

    @Test
    public void editEventGettingErrorEventAlreadyExisting() {
        initialize();
        tempDraftedEvent.setEventAds(tempAdsOption);
        tempDraftedEvent.setEventLocation(tempLocation);

        Assertions.assertThrows(EventNotFoundException.class, () -> {
            draftedEventRepositoryService.update(tempDraftedEvent);
        });
    }

    @Test
    public void editEventwithoutError() {
        initialize();
        tempDraftedEvent.setEventAds(tempAdsOption);
        tempDraftedEvent.setEventLocation(tempLocation);
        draftedEventRepositoryService.saveWhenCreating(tempDraftedEvent);
        DraftedEvent newTempDraftedEvent=createSecoundevent();
        newTempDraftedEvent.setId(tempDraftedEvent.getId());
        Assertions.assertDoesNotThrow(() -> {
            draftedEventRepositoryService.update(newTempDraftedEvent);
            Assertions.assertEquals(newTempDraftedEvent.getId(), tempDraftedEvent.getId());
        });
    }

//    @Test
//    public void testDeleteEventThrowsExceptionWhenEventNotFound() {
//        Assertions.assertThrows(EventNotFoundException.class, () -> {
//            draftedEventRepositoryService.delete(0);
//        });
//    }

//    @Test
//    public void testDeleteEventDeletesEventSuccessfully() {
//        initialize();
//        tempDraftedEvent.setEventAds(tempAdsOption);
//        tempDraftedEvent.setEventLocation(tempLocation);
//        draftedEventRepositoryService.saveWhenCreating(tempDraftedEvent);
//        Assertions.assertDoesNotThrow(() -> {
//            draftedEventRepositoryService.delete(tempDraftedEvent.getId());
//        });
//    }


    private void initialize() {
        createOrganizer();
        createAdsOption();
        createLocation();
        createEvent();
    }

    private void createOrganizer() {
        tempOrganizer = (Organizer) userCustomCreator.getAndSaveUser(Role.ORGANIZER);
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
        tempDraftedEvent = DraftedEvent.builder()
                .name("e5")
                .eventOrganizer(tempOrganizer)
                .description("...")
                .eventType(EventType.DRAFTEDEVENT)
                .seatTypes(new ArrayList<>()).build();
    }

    private void createLocation() {
        tempLocation = Location.builder()
                .country("Egypt")
                .city("Alex").build();
    }
    private DraftedEvent createSecoundevent(){
        Location location2 = Location.builder().country("mun").city("cairo").build();
        return DraftedEvent.builder()
                .id(tempDraftedEvent.getId())
                .eventAds(tempAdsOption)
                .eventLocation(location2)
                .name("e500")
                .eventDate(new Date(System.currentTimeMillis() + 100000))
                .eventOrganizer(tempOrganizer)
                .seatTypes(new ArrayList<>())
                .build();
    }
}