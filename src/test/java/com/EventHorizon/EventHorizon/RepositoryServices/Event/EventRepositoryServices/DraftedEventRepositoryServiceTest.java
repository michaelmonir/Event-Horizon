package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event.AdsOption;
import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.Location;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventTypeExceptions.NotDraftedEventException;
import com.EventHorizon.EventHorizon.Exceptions.Event.InvalidEventDataException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces.DraftedEventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DraftedEventRepositoryServiceTest {

    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private UserCustomCreator userCustomCreator;

    @Test
    public void getById() {
        Event event =  eventCustomCreator.getandSaveEvent(EventType.DRAFTEDEVENT);
        Event event1 = draftedEventRepositoryService.getById(event.getId());
        Assertions.assertEquals(event, event1);
    }

    @Test
    public void getByIdNotFound() {
        Assertions.assertThrows(EventNotFoundException.class,
                () -> draftedEventRepositoryService.getById(100008));
    }

    @Test
    public void getByIdLaunchedEvent() {
        Event event =  eventCustomCreator.getandSaveEvent(EventType.LAUNCHEDEVENT);
        Assertions.assertThrows(NotDraftedEventException.class,
                () -> draftedEventRepositoryService.getById(event.getId()));
    }

    @Test
    public void getExceptionOnSendingIdWhenCreating() {
        DraftedEvent event = (DraftedEvent) eventCustomCreator.getEvent(EventType.DRAFTEDEVENT);
        event.setId(5);
        Assertions.assertThrows(EventAlreadyExisting.class,
                () -> draftedEventRepositoryService.saveWhenCreating(event));
    }

    @Test
    public void createEventSuccessfully() {
        DraftedEvent event = (DraftedEvent) eventCustomCreator.getEvent(EventType.DRAFTEDEVENT);
        draftedEventRepositoryService.saveWhenCreating(event);
        Assertions.assertNotEquals(0, event.getId());
        Event eventfromDb = draftedEventRepositoryService.getById(event.getId());
        Assertions.assertEquals(event, eventfromDb);
    }

    @Test
    public void createEventWithUnsavedAdsOptionsGetsError() {
        DraftedEvent event = (DraftedEvent) eventCustomCreator.getEvent(EventType.DRAFTEDEVENT);
        event.setEventAds(new AdsOption(1000000, 1, "h"));
        Assertions.assertThrows(InvalidEventDataException.class,
                () -> draftedEventRepositoryService.saveWhenCreating(event));
    }

    @Test
    public void createEventWithUnsavedOrganizerGetsError() {
        DraftedEvent event = (DraftedEvent) eventCustomCreator.getEvent(EventType.DRAFTEDEVENT);
        Organizer organizer = (Organizer) this.userCustomCreator.getUser(Role.ORGANIZER);
        event.setEventOrganizer(organizer);
        Assertions.assertThrows(InvalidEventDataException.class,
                () -> draftedEventRepositoryService.saveWhenCreating(event));
    }

    @Test
    public void creatingEventSavesLocation() {
        DraftedEvent event = (DraftedEvent) eventCustomCreator.getEvent(EventType.DRAFTEDEVENT);
        event.setEventLocation(new Location("Egypt", "Cairo", "aaaaaaa"));
        draftedEventRepositoryService.saveWhenCreating(event);
        Assertions.assertNotEquals(0, event.getEventLocation().getId());
    }

//    @Test
//    public void editEventGettingErrorEventAlreadyExisting() {
//        initialize();
//        tempDraftedEvent.setEventAds(tempAdsOption);
//        tempDraftedEvent.setEventLocation(tempLocation);
//
//        Assertions.assertThrows(EventNotFoundException.class, () -> {
//            draftedEventRepositoryServiceImpl.update(tempDraftedEvent);
//        });
//    }
//
//    @Test
//    public void editEventwithoutError() {
//        initialize();
//        tempDraftedEvent.setEventAds(tempAdsOption);
//        tempDraftedEvent.setEventLocation(tempLocation);
//        draftedEventRepositoryServiceImpl.saveWhenCreating(tempDraftedEvent);
//        DraftedEvent newTempDraftedEvent=createSecoundevent();
//        newTempDraftedEvent.setId(tempDraftedEvent.getId());
//        Assertions.assertDoesNotThrow(() -> {
//            draftedEventRepositoryServiceImpl.update(newTempDraftedEvent);
//            Assertions.assertEquals(newTempDraftedEvent.getId(), tempDraftedEvent.getId());
//        });
//    }
}
