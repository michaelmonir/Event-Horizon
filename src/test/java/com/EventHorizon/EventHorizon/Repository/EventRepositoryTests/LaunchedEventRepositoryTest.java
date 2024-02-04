package com.EventHorizon.EventHorizon.Repository.EventRepositoryTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Repository.Event.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.Event.LaunchedEventRepository;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LaunchedEventRepositoryTest {

    @Autowired
    private LaunchedEventRepository launchedEventRepository;

    @Autowired
    private AdsOptionRepository adsOptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCustomCreator userCustomCreator;

    private LaunchedEvent launchedEvent;
    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;

    @Test
    public void createLaunchedEvent(){
        insialize();
        launchedEventRepository.save(launchedEvent);
        Assertions.assertNotEquals(0, launchedEvent.getId());
    }
    @Test
    public void findNotExistedLaunchedEventById() {
        Optional<LaunchedEvent> event = launchedEventRepository.findById(0);
        assertFalse(event.isPresent());
    }
    @Test
    public void findExistedLaunchedEventById() {
        insialize();
        launchedEventRepository.save(launchedEvent);
        Optional<LaunchedEvent> findedEvent = launchedEventRepository.findById(launchedEvent.getId());
        assertTrue(findedEvent.isPresent());
    }
    @Test
    public void createLaunchedEventWithoutEvent() {
        LaunchedEvent launchedEvent=LaunchedEvent.builder().build();
        Assertions.assertThrows(RuntimeException.class, () -> {
            launchedEventRepository.save(launchedEvent);
        });
    }


    private void insialize(){
        createOrganizer();
        createAdsOption();
        createEvent();
    }
    private void  createOrganizer(){
        Organizer organizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);
        userRepository.save(organizer);
       tempOrganizer=organizer;
    }
    public void createAdsOption(){
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1)
                .build();
        adsOptionRepository.save(adsOption);
        tempAdsOption=adsOption;

    }
    private void createEvent(){
        launchedEvent= LaunchedEvent.builder()
                .name("e5")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer)
                .description("...").build();
    }

}