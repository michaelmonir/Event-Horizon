package com.EventHorizon.EventHorizon.Repository.EventCreation.EventService;

import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
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
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    InformationCreator informationCreator;

    private Event tempEvent;
    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;
    private Location tempLocation;


    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        Event event = new Event();
        event.setId(5);
        event.setName("Michael's Event");
        Assertions.assertThrows(EventAlreadyExisting.class, () -> {
            eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        });
    }

    @Test
    public void addEventNotGettingError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempEvent);
            Assertions.assertNotEquals(0, tempEvent.getId());
        });
    }


    @Test
    public void editEventGettingErrorEventAlreadyExisting() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempEvent.setId(34);
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            eventRepositoryService.updateEventAndHandleNotFound(tempEvent);
        });
    }

    @Test
    public void editEventwithoutError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempEvent);
        Location location2 = Location.builder().country("mun").city("cairo").build();
        Event newEvent = Event.builder()
                .id(tempEvent.getId())
                .eventAds(tempAdsOption)
                .eventLocation(location2)
                .name("e500")
                .eventOrganizer(tempOrganizer)
                .build();
        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.updateEventAndHandleNotFound(newEvent);
            Assertions.assertEquals(tempEvent.getId(), newEvent.getId());
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

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempEvent);
        Assertions.assertDoesNotThrow(() -> {
            eventRepositoryService.deleteEvent(tempEvent.getId());
        });
    }

    @Test
    public void testGetEventDetailsDtoThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            eventRepositoryService.getViewEventDTO(0);
        });
    }

    @Test
    public void testGetEventDetailsDtoReturnsCorrectDto() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempEvent);

        ViewEventDto eventDetailsDto = Assertions.assertDoesNotThrow(() ->
                eventRepositoryService.getViewEventDTO(tempEvent.getId())
        );

        Assertions.assertEquals(tempEvent.getName(), eventDetailsDto.getName());
        Assertions.assertEquals(tempEvent.getDescription(), eventDetailsDto.getDescription());
    }

    @Test
    public void testGetEventHeaderDtoThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            eventRepositoryService.getEventHeaderDto(0);
        });
    }

    @Test
    public void testGetEventHeaderDtoReturnsCorrectDto() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempEvent);
        EventHeaderDto eventHeaderDto = Assertions.assertDoesNotThrow(() ->
                eventRepositoryService.getEventHeaderDto(tempEvent.getId())
        );
        Assertions.assertEquals(tempEvent.getName(), eventHeaderDto.getName());
    }

    @Test
    public void testGetAllEventsHeaderDtoReturnsCorrectList() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        Event event2 = Event.builder()
                .eventAds(tempAdsOption)
                .name("Event2")
                .eventOrganizer(tempOrganizer)
                .build();

        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempEvent);
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event2);

        int pageIndex = 0;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<EventHeaderDto> eventHeaderDtos = eventRepositoryService.getAllEventsHeaderDto(pageRequest);
        Assertions.assertFalse(eventHeaderDtos.isEmpty());
    }

    @Test
    public void testGetAllEventsHeaderDtoReturnsErrors() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        Event event2 = Event.builder()
                .eventAds(tempAdsOption)
                .name("Event2")
                .eventOrganizer(tempOrganizer)
                .build();

        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempEvent);
        eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event2);

        int pageIndex = 10;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Assertions.assertDoesNotThrow(() -> {
            List<EventHeaderDto> eventHeaderDtos = eventRepositoryService.getAllEventsHeaderDto(pageRequest);
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
        adsOptionRepositry.save(adsOption);
        tempAdsOption = adsOption;

    }

    private void createEvent() {
        Event event = Event.builder()
                .name("e5")
                .eventOrganizer(tempOrganizer)
                .description("...").build();
        tempEvent = event;
    }

    private void createLocation() {
        Location location = Location.builder()
                .country("Egypt")
                .city("Alex").build();
        tempLocation = location;
    }
}