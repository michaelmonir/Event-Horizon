package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventHeaderDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.InformationCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions.NotFutureEventException;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.UserRepositories.OrganizerRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Date;
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
    InformationCustomCreator informationCreator;
    
    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;
    private Location tempLocation;
    private LaunchedEvent tempLaunchedEvent;


    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        LaunchedEvent event = new LaunchedEvent();
        event.setId(5);
        Assertions.assertThrows(EventAlreadyExisting.class, () -> {
            launchedEventRepositoryService.saveWhenCreating(event);
        });
    }
    @Test
    public void addingPastEvent() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEventLocation(tempLocation);
        tempLaunchedEvent.setEventDate(DateFunctions.getYesterDaysDate());
        Assertions.assertThrows(NotFutureEventException.class, () -> {
            launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
        });
    }

    @Test
    public void addEventNotGettingError() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEventLocation(tempLocation);
        Assertions.assertDoesNotThrow(() -> {
            launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
            Assertions.assertNotEquals(0, tempLaunchedEvent.getId());
        });
    }


    @Test
    public void editEventGettingErrorEventAlreadyExisting() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEventLocation(tempLocation);
        tempLaunchedEvent.setId(500);
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            launchedEventRepositoryService.update(tempLaunchedEvent);
        });
    }
    @Test
    public void editPastEvent() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEventLocation(tempLocation);
        Assertions.assertThrows(NotFutureEventException.class, () -> {
            tempLaunchedEvent.setEventDate(DateFunctions.getYesterDaysDate());
            launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
            launchedEventRepositoryService.update(tempLaunchedEvent);
        });
    }

    @Test
    public void editEventWithoutError() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEventLocation(tempLocation);
        launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
        LaunchedEvent newLaunchedEvent =createSecoundevent();
        newLaunchedEvent.setId(tempLaunchedEvent.getId());
        Assertions.assertDoesNotThrow(() -> {
            launchedEventRepositoryService.update(newLaunchedEvent);
            Assertions.assertEquals(tempLaunchedEvent.getId(), newLaunchedEvent.getId());
        });
    }

    @Test
    public void testDeleteEventThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            launchedEventRepositoryService.delete(0);
        });
    }

    @Test
    public void testDeleteEventDeletesEventSuccessfully() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEventLocation(tempLocation);
        launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
        Assertions.assertDoesNotThrow(() -> {
            launchedEventRepositoryService.delete(tempLaunchedEvent.getId());
        });
    }

    @Test
    public void testGetEventHeaderDtoThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            launchedEventRepositoryService.getEventHeaderDto(0);
        });
    }

    @Test
    public void testGetEventHeaderDtoReturnsCorrectDto() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        tempLaunchedEvent.setEventLocation(tempLocation);
        launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
        EventHeaderDto eventHeaderDto = Assertions.assertDoesNotThrow(() ->
                launchedEventRepositoryService.getEventHeaderDto(tempLaunchedEvent.getId())
        );
        Assertions.assertEquals(tempLaunchedEvent.getName(), eventHeaderDto.getName());
    }

    @Test
    public void testGetAllEventsHeaderDtoReturnsCorrectList() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        LaunchedEvent launchedEvent2=createSecoundevent();
        launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
        launchedEventRepositoryService.saveWhenCreating(launchedEvent2);
        int pageIndex = 0;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        List<EventHeaderDto> eventHeaderDtos = launchedEventRepositoryService.getAllEventsHeaderDto(pageRequest);
        Assertions.assertFalse(eventHeaderDtos.isEmpty());
    }

    @Test
    public void testGetAllEventsHeaderDtoReturnsErrors() {
        initialize();
        tempLaunchedEvent.setEventAds(tempAdsOption);
        LaunchedEvent launchedEvent2=createSecoundevent();
        launchedEventRepositoryService.saveWhenCreating(tempLaunchedEvent);
        launchedEventRepositoryService.saveWhenCreating(launchedEvent2);
        int pageIndex = 10;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Assertions.assertDoesNotThrow(() -> {
            List<EventHeaderDto> eventHeaderDtos = launchedEventRepositoryService.getAllEventsHeaderDto(pageRequest);
        });
    }

    private void initialize() {
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
        tempLaunchedEvent = LaunchedEvent.builder()
                .name("e5")
                .eventOrganizer(tempOrganizer)
                .description("...")
                .eventDate(new Date(System.currentTimeMillis() + 100000))
                .seatTypes(new ArrayList<>()).build();
    }

    private void createLocation() {
        tempLocation = Location.builder()
                .country("Egypt")
                .city("Alex").build();
    }
    private LaunchedEvent createSecoundevent(){
        Location location2 = Location.builder().country("mun").city("cairo").build();
       return LaunchedEvent.builder()
                .id(tempLaunchedEvent.getId())
                .eventAds(tempAdsOption)
                .eventLocation(location2)
                .name("e500")
                .eventDate(new Date(System.currentTimeMillis() + 100000))
                .eventOrganizer(tempOrganizer)
               .seatTypes(new ArrayList<>())
                .build();
    }
}