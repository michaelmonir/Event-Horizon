package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.InformationCustomCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DraftedEventRepositoryTest {
    @Autowired
    private DraftedEventRepository tempEventRepository;

    @Autowired
    private AdsOptionRepository adsOptionRepository;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private InformationCustomCreator informationCreator;

    private DraftedEvent tempEvent;
    private AdsOption tempAdsOption;
    private Organizer tempOrganizer;

    @Test
    public void createDraftedEvent(){
        insialize();
        tempEventRepository.save(tempEvent);
        Assertions.assertNotEquals(0, tempEvent.getId());
    }
    @Test
    public void findNotExistedDraftedEventById() {
        Optional<DraftedEvent> event = tempEventRepository.findById(0);
        assertFalse(event.isPresent());
    }
    @Test
    public void findExistedDraftedEventById() {
        insialize();
        tempEventRepository.save(tempEvent);
        Optional<DraftedEvent> findedEvent = tempEventRepository.findById(tempEvent.getId());
        assertTrue(findedEvent.isPresent());
    }
    @Test
    public void createDraftedEventWithoutEvent() {
        DraftedEvent tempEvent=DraftedEvent.builder().build();
        Assertions.assertThrows(RuntimeException.class, () -> {
            tempEventRepository.save(tempEvent);
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
        adsOptionRepository.save(adsOption);
        tempAdsOption=adsOption;

    }
    private void createEvent(){
        tempEvent= DraftedEvent.builder()
                .name("e5")
                .eventAds(tempAdsOption)
                .eventOrganizer(tempOrganizer)
                .description("...").build();
    }

}