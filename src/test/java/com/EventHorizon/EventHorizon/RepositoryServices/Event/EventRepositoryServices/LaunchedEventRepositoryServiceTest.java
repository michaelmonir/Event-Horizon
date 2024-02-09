package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event.*;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventTypeExceptions.NotFutureEventException;
import com.EventHorizon.EventHorizon.Exceptions.Event.InvalidEventDataException;
import com.EventHorizon.EventHorizon.Exceptions.Event.NotLaunchedEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LaunchedEventRepositoryServiceTest {

    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private UserCustomCreator userCustomCreator;

    @Test
    public void getById() {
        Event event =  eventCustomCreator.getAndSaveEvent(EventType.LAUNCHEDEVENT);
        Event event1 = launchedEventRepositoryService.getById(event.getId());
        Assertions.assertEquals(event, event1);
    }

    @Test
    public void getByIdNotFound() {
        Assertions.assertThrows(EventNotFoundException.class,
                () -> launchedEventRepositoryService.getById(100008));
    }

    @Test
    public void getByIdDraftedEvent() {
        Event event =  eventCustomCreator.getAndSaveEvent(EventType.DRAFTEDEVENT);
        Assertions.assertThrows(NotLaunchedEventException.class,
                () -> launchedEventRepositoryService.getById(event.getId()));
    }

    @Test
    public void launchEventSuccessfully() {
        LaunchedEvent event = (LaunchedEvent) eventCustomCreator.getEventWithoutSeatTypes(EventType.LAUNCHEDEVENT);
        launchedEventRepositoryService.saveWhenLaunching(event);
        Assertions.assertNotEquals(0, event.getId());
        Assertions.assertEquals(event, launchedEventRepositoryService.getById(event.getId()));
    }

    @Test
    public void launchEventWithPositiveId() {
        LaunchedEvent event = (LaunchedEvent) eventCustomCreator.getEventWithoutSeatTypes(EventType.LAUNCHEDEVENT);
        event.setId(5);
        Assertions.assertThrows(EventAlreadyExisting.class,
                () -> launchedEventRepositoryService.saveWhenLaunching(event));
    }

    @Test
    public void launchEventWithPastDate() {
        LaunchedEvent event = (LaunchedEvent) eventCustomCreator.getEventWithoutSeatTypes(EventType.LAUNCHEDEVENT);
        event.setEventDate(DateFunctions.getYesterDaysDate());
        Assertions.assertThrows(NotFutureEventException.class,
                () -> launchedEventRepositoryService.saveWhenLaunching(event));
    }

    @Test
    public void launchEventWithUnsavedAdsOptionsGetsError() {
        LaunchedEvent event = (LaunchedEvent) eventCustomCreator.getEventWithoutSeatTypes(EventType.LAUNCHEDEVENT);
        event.setEventAds(new AdsOption(1000000, 1, "h"));
        Assertions.assertThrows(InvalidEventDataException.class,
                () -> launchedEventRepositoryService.saveWhenLaunching(event));
    }

    @Test
    public void launchEventWithUnsavedOrganizerGetsError() {
        LaunchedEvent event = (LaunchedEvent) eventCustomCreator.getEventWithoutSeatTypes(EventType.LAUNCHEDEVENT);
        Organizer organizer = (Organizer) this.userCustomCreator.getUser(Role.ORGANIZER);
        event.setEventOrganizer(organizer);
        Assertions.assertThrows(InvalidEventDataException.class,
                () -> launchedEventRepositoryService.saveWhenLaunching(event));
    }

    @Test
    public void launchingEventSavesLocation() {
        LaunchedEvent event = (LaunchedEvent) eventCustomCreator.getEventWithoutSeatTypes(EventType.LAUNCHEDEVENT);
        event.setEventLocation(new Location("Egypt", "Cairo", "aaaaaaa"));
        launchedEventRepositoryService.saveWhenLaunching(event);
        Assertions.assertNotEquals(0, event.getEventLocation().getId());
    }

    //    @Test
//    public void testGetEventHeaderDtoThrowsExceptionWhenEventNotFound() {
//        Assertions.assertThrows(EventNotFoundException.class, () -> {
//            launchedEventRepositoryService.getEventHeaderDto(0);
//        });
//    }

//    @Test
//    public void testGetEventHeaderDtoReturnsCorrectDto() {
//        initialize();
//        tempLaunchedEvent.setEventAds(tempAdsOption);
//        tempLaunchedEvent.setEventLocation(tempLocation);
//        launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
//        EventHeaderDto eventHeaderDto = Assertions.assertDoesNotThrow(() ->
//                launchedEventRepositoryService.getEventHeaderDto(tempLaunchedEvent.getId())
//        );
//        Assertions.assertEquals(tempLaunchedEvent.getName(), eventHeaderDto.getName());
//    }

//    @Test
//    public void testGetAllEventsHeaderDtoReturnsCorrectList() {
//        initialize();
//        tempLaunchedEvent.setEventAds(tempAdsOption);
//        LaunchedEvent launchedEvent2=createSecoundevent();
//        launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
//        launchedEventRepositoryService.saveWhenCreating(launchedEvent2);
//        int pageIndex = 0;
//        int pageSize = 10;
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        List<EventHeaderDto> eventHeaderDtos = launchedEventRepositoryService.getAllEventsHeaderDto(pageRequest);
//        Assertions.assertFalse(eventHeaderDtos.isEmpty());
//    }
//
//    @Test
//    public void testGetAllEventsHeaderDtoReturnsErrors() {
//        initialize();
//        tempLaunchedEvent.setEventAds(tempAdsOption);
//        LaunchedEvent launchedEvent2=createSecoundevent();
//        launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
//        launchedEventRepositoryService.saveWhenCreating(launchedEvent2);
//        int pageIndex = 10;
//        int pageSize = 10;
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        Assertions.assertDoesNotThrow(() -> {
//            List<EventHeaderDto> eventHeaderDtos = launchedEventRepositoryService.getAllEventsHeaderDto(pageRequest);
//        });
//    }
}
