package com.EventHorizon.EventHorizon.EventCreation.EventService;

import com.EventHorizon.EventHorizon.EventCreation.AdsOption;
import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.EventCreation.EventDto.EventDetailsDto;
import com.EventHorizon.EventHorizon.EventCreation.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.EventCreation.Location;
import com.EventHorizon.EventHorizon.Exceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import java.util.List;




@SpringBootTest
class EventRepositoryServiceTest {


    @Autowired
    private EventRepositoryService eventRepositoryService;

    @Autowired
    private AdsOptionRepositry adsOptionRepositry;

    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        Event event = new Event();
        event.setId(5);
        event.setName("Michael's Event");

        Assertions.assertThrows(EventAlreadyExisting.class, () -> {
            eventRepositoryService.saveEventWhenCreating(event);
        });
    }

    @Test
    public void addEventNotGettingError() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1 = Location.builder().country("Egypt").city("Cairo").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("e45")
                .description("...")
                .build();

        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.saveEventWhenCreating(event);
            Assertions.assertNotEquals(0, event.getId());
        });
    }

    @Test
    public void editEventGettingErrorEventNotFoundException() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1 = Location.builder().country("Egypt").city("Cairo").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("11")
                .description("...")
                .build();

        Assertions.assertThrows(EventNotFoundException.class, () -> {
            eventRepositoryService.updateEvent(0, event);
        });
    }

    @Test
    public void editEventGettingErrorEventAlreadyExisting() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1 = Location.builder().country("aswan").city("cairo").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("e45")
                .description("...")
                .id(27)
                .build();

        Assertions.assertThrows(EventAlreadyExisting.class, () -> {
            eventRepositoryService.updateEvent(34, event);
        });
    }

    @Test
    public void editEventwithoutError() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);
        Location location1 = Location.builder().country("qula").city("cairo").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("e800")
                .description("...")
                .build();
        eventRepositoryService.saveEventWhenCreating(event);
        Location location2 = Location.builder().country("mun").city("cairo").build();
        Event newEvent = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location2)
                .name("e500")
                .description("newevent")
                .build();

        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.updateEvent(event.getId(), newEvent);
            Assertions.assertEquals(event.getId(), newEvent.getId());
        });
    }

    @Test
    public void testDeleteEventThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            eventRepositoryService.deleteEvent(0);
        });
    }

    @Test
    public void testDeleteEventDeletesEventSuccessfully() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);

        Location location = Location.builder().country("Egypt").city("Cairo").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location)
                .name("EventToDelete")
                .description("...")
                .build();

        eventRepositoryService.saveEventWhenCreating(event);

        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.deleteEvent(event.getId());
        });
    }

    @Test
    public void testGetEventDetailsDtoThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            eventRepositoryService.getEventDeatilsDto(0);
        });
    }

    @Test
    public void testGetEventDetailsDtoReturnsCorrectDto() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);

        Location location = Location.builder().country("Egypt").city("Cairo").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location)
                .name("EventDetailsDtoTest")
                .description("...")
                .build();

        eventRepositoryService.saveEventWhenCreating(event);

        EventDetailsDto eventDetailsDto = Assertions.assertDoesNotThrow(() ->
                eventRepositoryService.getEventDeatilsDto(event.getId())
        );

        Assertions.assertEquals(event.getName(), eventDetailsDto.getName());
        Assertions.assertEquals(event.getDescription(), eventDetailsDto.getDescription());
    }

    @Test
    public void testGetEventHeaderDtoThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            eventRepositoryService.getEventHeaderDto(0);
        });
    }

    @Test
    public void testGetEventHeaderDtoReturnsCorrectDto() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);

        Location location = Location.builder().country("Egypt").city("Cairo").build();
        Event event = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location)
                .name("EventHeaderDtoTest")
                .description("...")
                .build();

        eventRepositoryService.saveEventWhenCreating(event);

        EventHeaderDto eventHeaderDto = Assertions.assertDoesNotThrow(() ->
                eventRepositoryService.getEventHeaderDto(event.getId())
        );

        Assertions.assertEquals(event.getName(), eventHeaderDto.getName());
    }

    @Test
    public void testGetAllEventsHeaderDtoReturnsCorrectList() {
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(2)
                .build();
        adsOptionRepositry.save(adsOption);

        Location location1 = Location.builder().country("Egypt").city("Cairo").build();
        Event event1 = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location1)
                .name("Event1")
                .description("...")
                .build();

        Location location2 = Location.builder().country("USA").city("New York").build();
        Event event2 = Event.builder()
                .eventAds(adsOption)
                .eventLocation(location2)
                .name("Event2")
                .description("...")
                .build();

        eventRepositoryService.saveEventWhenCreating(event1);
        eventRepositoryService.saveEventWhenCreating(event2);

        int pageIndex = 0;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);

        List<EventHeaderDto> eventHeaderDtos = eventRepositoryService.getAllEventsHeaderDto(pageRequest);

        Assertions.assertFalse(eventHeaderDtos.isEmpty());
    }
}