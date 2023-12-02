package com.EventHorizon.EventHorizon.Repository.EventCreation.EventService;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.LocationComponent.LocationRepositoryService;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocationRepositoryServiceTest {
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private LocationRepositoryService locationRepositoryService;
    @Autowired
    private AdsOptionRepositry adsOptionRepositry;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    InformationCreator informationCreator;
    @Test
    public void testDeletionOfLocationByEcventId(){
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer = Organizer.builder().information(information).build();
        organizerRepository.save(organizer);
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1).build();
        adsOptionRepositry.save(adsOption);
        Location location= Location.builder()
                .country("mozmbeq")
                .city("Alex").build();
        Event event=Event.builder()
                .name("micol")
                .eventLocation(location)
                .eventAds(adsOption)
                .eventOrganizer(organizer)
                .description("neo").build();
         eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        locationRepositoryService.deleteLocationById(event.getId());
    }

}