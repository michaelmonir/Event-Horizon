package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Location;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories.UserRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.LocationComponent.LocationRepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocationRepositoryServiceTest {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private LocationRepositoryService locationRepositoryService;
    @Autowired
    private AdsOptionRepository adsOptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserCustomCreator userCustomCreator;

    @Test
    public void testDeletionOfLocationByEcventId(){
        Organizer organizer = (Organizer) userCustomCreator.getUser(Role.ORGANIZER);

        userRepository.save(organizer);
        AdsOption adsOption = AdsOption.builder()
                .name("p")
                .priority(1).build();
        adsOptionRepository.save(adsOption);
        Location location= Location.builder()
                .country("mozmbeq")
                .city("Alex").build();
        Event event=Event.builder()
                .name("micol")
                .eventLocation(location)
                .eventAds(adsOption)
                .eventOrganizer(organizer)
                .description("neo").build();
        eventRepository.save(event);
        locationRepositoryService.deleteLocationById(event.getId());
    }
}