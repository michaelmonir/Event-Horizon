package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LaunchedEventRepositoryTest {

    @Autowired
    private LaunchedEventRepository launchedEventRepository;

    @Autowired
    private AdsOptionRepositry adsOptionRepositry;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private InformationCreator informationCreator;

    private Event tempEvent;
    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;

    @Test
    public void createLaunchedEvent(){
        insialize();
        LaunchedEvent launchedEvent=LaunchedEvent.builder()
                .event(tempEvent).build();
        launchedEventRepository.save(launchedEvent);
        Assertions.assertNotEquals(0, launchedEvent.getId());
    }
    @Test
    public void findNotExistedLaunchedEventById() {
        Optional<LaunchedEvent> event = launchedEventRepository.findById(0);
        Assertions.assertEquals(event.isPresent(), false);
    }
    @Test
    public void findExistedLaunchedEventById() {
        insialize();
        LaunchedEvent launchedEvent=LaunchedEvent.builder()
                .event(tempEvent).build();
        launchedEventRepository.save(launchedEvent);
        Optional<LaunchedEvent> findedEvent = launchedEventRepository.findById(launchedEvent.getId());
        Assertions.assertEquals(findedEvent.isPresent(), true);
    }
    @Test
    public void createLaunchedEventWithoutEvent() {
        LaunchedEvent launchedEvent=LaunchedEvent.builder().build();
        Assertions.assertThrows(RuntimeException.class, () -> {
            launchedEventRepository.save(launchedEvent);
        });
    }
    @Test
    public void testOneToOneRelationBetwenLaunchedEventAndEventWithError() {
        insialize();
        LaunchedEvent launchedEvent=LaunchedEvent.builder()
                .event(tempEvent).build();
        launchedEventRepository.save(launchedEvent);
        LaunchedEvent launchedEvent2=LaunchedEvent.builder()
                .event(tempEvent).build();
        Assertions.assertThrows(RuntimeException.class, () -> {
            launchedEventRepository.save(launchedEvent2);
        });

    }
    @Test
    public void testOneToOneRelationBetwenLaunchedEventAndEventWithOutError() {
        insialize();
        LaunchedEvent launchedEvent=LaunchedEvent.builder()
                .event(tempEvent).build();
        launchedEventRepository.save(launchedEvent);
        LaunchedEvent launchedEvent2=LaunchedEvent.builder()
                .event(tempEvent).build();
        tempEvent.setId(0);
        Assertions.assertDoesNotThrow(() -> {
            launchedEventRepository.save(launchedEvent2);
        });

    }


    private void insialize(){
        createOrganizer();
        createAdsOption();
        createEvent();
    }
    private void  createOrganizer(){
        Information information = informationCreator.getInformation(Role.ORGANIZER);
        Organizer organizer = Organizer.builder().information(information).build();
        organizerRepository.save(organizer);
       tempOrganizer=organizer;
    }
    public void createAdsOption(){
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepositry.save(adsOption);
        tempAdsOption=adsOption;

    }
    private void createEvent(){
        Event event = Event.builder()
                .name("e5")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer)
                .description("...").build();
        tempEvent=event;
    }

}