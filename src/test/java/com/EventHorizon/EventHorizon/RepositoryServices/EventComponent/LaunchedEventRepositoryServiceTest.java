package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.ViewEventDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@SpringBootTest
class LaunchedEventRepositoryServiceTest {
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;

    @Autowired
    private AdsOptionRepository adsOptionRepository;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    InformationCreator informationCreator;

    private Event tempEvent;
    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;
    private Location tempLocation;
    private LaunchedEvent tempLaunchedEvent;


    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        LaunchedEvent event = new LaunchedEvent();
        event.setId(5);
        Assertions.assertThrows(EventAlreadyExisting.class, () -> {
            launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        });
    }

    @Test
    public void addEventNotGettingError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempLaunchedEvent.setEvent(tempEvent);
        Assertions.assertDoesNotThrow(() -> {
            launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempLaunchedEvent);
            Assertions.assertNotEquals(0, tempLaunchedEvent.getId());
        });
    }


    @Test
    public void editEventGettingErrorEventAlreadyExisting() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempLaunchedEvent.setEvent(tempEvent);
        tempLaunchedEvent.setId(500);
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            launchedEventRepositoryService.updateEventAndHandleNotFound(tempLaunchedEvent);
        });
    }

    @Test
    public void editEventwithoutError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempLaunchedEvent.setEvent(tempEvent);
        launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempLaunchedEvent);
        Location location2 = Location.builder().country("mun").city("cairo").build();
        Event newEvent = Event.builder()
                .id(tempEvent.getId())
                .eventAds(tempAdsOption)
                .eventLocation(location2)
                .name("e500")
                .eventOrganizer(tempOrganizer)
                .build();
        LaunchedEvent newLaunchedEvent =LaunchedEvent.builder().event(newEvent).build();
        newLaunchedEvent.setId(tempLaunchedEvent.getId());
        Assertions.assertDoesNotThrow(() -> {
            launchedEventRepositoryService.updateEventAndHandleNotFound(newLaunchedEvent);
            Assertions.assertEquals(tempLaunchedEvent.getId(), newLaunchedEvent.getId());
        });
    }

    @Test
    public void testDeleteEventThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            launchedEventRepositoryService.deleteEvent(0);
        });
    }

    @Test
    public void testDeleteEventDeletesEventSuccessfully() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempLaunchedEvent.setEvent(tempEvent);
        launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempLaunchedEvent);
        Assertions.assertDoesNotThrow(() -> {
            launchedEventRepositoryService.deleteEvent(tempLaunchedEvent.getId());
        });
    }

    @Test
    public void testGetEventDetailsDtoThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            launchedEventRepositoryService.getViewEventDTO(0);
        });
    }

    @Test
    public void testGetEventDetailsDtoReturnsCorrectDto() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempLaunchedEvent.setEvent(tempEvent);
        launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempLaunchedEvent);

        ViewEventDto eventDetailsDto = Assertions.assertDoesNotThrow(() ->
                launchedEventRepositoryService.getViewEventDTO(tempLaunchedEvent.getId())
        );

        Assertions.assertEquals(tempLaunchedEvent.getName(), eventDetailsDto.getName());
        Assertions.assertEquals(tempLaunchedEvent.getDescription(), eventDetailsDto.getDescription());
    }

    @Test
    public void testGetEventHeaderDtoThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            launchedEventRepositoryService.getEventHeaderDto(0);
        });
    }

    @Test
    public void testGetEventHeaderDtoReturnsCorrectDto() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempLaunchedEvent.setEvent(tempEvent);
        launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempLaunchedEvent);
        EventHeaderDto eventHeaderDto = Assertions.assertDoesNotThrow(() ->
                launchedEventRepositoryService.getEventHeaderDto(tempLaunchedEvent.getId())
        );
        Assertions.assertEquals(tempLaunchedEvent.getName(), eventHeaderDto.getName());
    }

    @Test
    public void testGetAllEventsHeaderDtoReturnsCorrectList() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEvent(tempEvent);
        Event event2 = Event.builder()
                .eventAds(tempAdsOption)
                .name("Event2")
                .eventOrganizer(tempOrganizer)
                .build();
        LaunchedEvent launchedEvent2=LaunchedEvent.builder().event(event2).build();
        launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempLaunchedEvent);
        launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(launchedEvent2);

        int pageIndex = 0;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<EventHeaderDto> eventHeaderDtos = launchedEventRepositoryService.getAllEventsHeaderDto(pageRequest);
        Assertions.assertFalse(eventHeaderDtos.isEmpty());
    }

    @Test
    public void testGetAllEventsHeaderDtoReturnsErrors() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEvent(tempEvent);
        Event event2 = Event.builder()
                .eventAds(tempAdsOption)
                .name("Event2")
                .eventOrganizer(tempOrganizer)
                .build();
        LaunchedEvent launchedEvent2=LaunchedEvent.builder().event(event2).build();
        launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempLaunchedEvent);
        launchedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(launchedEvent2);

        int pageIndex = 10;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Assertions.assertDoesNotThrow(() -> {
            List<EventHeaderDto> eventHeaderDtos = launchedEventRepositoryService.getAllEventsHeaderDto(pageRequest);
        });
    }

    private void insialize() {
        createOrganizer();
        createAdsOption();
        createLocation();
        createEvent();
        this.tempLaunchedEvent=new LaunchedEvent();
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