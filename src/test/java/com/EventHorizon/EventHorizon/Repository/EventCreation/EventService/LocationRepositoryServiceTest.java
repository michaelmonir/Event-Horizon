package com.EventHorizon.EventHorizon.Repository.EventCreation.EventService;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepositry;
import com.EventHorizon.EventHorizon.RepositoryServices.EventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.LocationRepositoryService;
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
    @Test
    public void testDeletionOfLocationByEcventId(){
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
                .description("neo").build();
         eventRepositoryService.saveEventWhenCreatingAndHandleAlreadyExisting(event);
        locationRepositoryService.deleteLocationById(event.getId());
    }

}