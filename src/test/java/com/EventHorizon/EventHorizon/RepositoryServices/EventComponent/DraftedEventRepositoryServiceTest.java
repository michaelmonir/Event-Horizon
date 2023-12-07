package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.*;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DraftedEventRepositoryServiceTest {
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;
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
    private DraftedEvent tempDraftedEvent;

    @Test
    public void testGettingExceptionOnSendingIdWhenCreating() {
        DraftedEvent event = new DraftedEvent();
        event.setId(5);
        Assertions.assertThrows(EventAlreadyExisting.class, () -> {
            draftedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        });
    }

    @Test
    public void addEventNotGettingError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempDraftedEvent=DraftedEvent.builder().event(tempEvent).build();
        Assertions.assertDoesNotThrow(() -> {
            draftedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempDraftedEvent);
            Assertions.assertNotEquals(0, tempEvent.getId());
        });
    }


    @Test
    public void editEventGettingErrorEventAlreadyExisting() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempDraftedEvent=DraftedEvent.builder().event(tempEvent).build();
        tempEvent.setId(34);
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            draftedEventRepositoryService.updateEventAndHandleNotFound(tempDraftedEvent);
        });
    }

    @Test
    public void editEventwithoutError() {
        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempDraftedEvent=DraftedEvent.builder().event(tempEvent).build();
        draftedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempDraftedEvent);
        DraftedEvent newTempDraftedEvent=DraftedEvent.builder().event(createSecoundevent()).build();
        newTempDraftedEvent.setId(tempDraftedEvent.getId());
        Assertions.assertDoesNotThrow(() -> {
            draftedEventRepositoryService.updateEventAndHandleNotFound(newTempDraftedEvent);
            Assertions.assertEquals(newTempDraftedEvent.getId(), tempDraftedEvent.getId());
        });
    }

    @Test
    public void testDeleteEventThrowsExceptionWhenEventNotFound() {
        Assertions.assertThrows(EventNotFoundException.class, () -> {
            draftedEventRepositoryService.deleteEvent(0);
        });
    }

    @Test
    public void testDeleteEventDeletesEventSuccessfully() {

        insialize();
        tempEvent.setEventAds(tempAdsOption);
        tempEvent.setEventLocation(tempLocation);
        tempDraftedEvent=DraftedEvent.builder().event(tempEvent).build();
        draftedEventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(tempDraftedEvent);
        Assertions.assertDoesNotThrow(() -> {
            draftedEventRepositoryService.deleteEvent(tempDraftedEvent.getId());
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
    private Event createSecoundevent(){
        Location location2 = Location.builder().country("mun").city("cairo").build();
        return Event.builder()
                .id(tempEvent.getId())
                .eventAds(tempAdsOption)
                .eventLocation(location2)
                .name("e500")
                .eventDate(new Date(System.currentTimeMillis() + 100000))
                .eventOrganizer(tempOrganizer)
                .build();
    }
}