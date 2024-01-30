package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories.UserRepository;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EventCustomCreator
{
    @Autowired
    private AdsOptionCustomCreator adsOptionCustomCreator;
    @Autowired
    private UserCustomCreator userCustomCreator;
    @Autowired
    private LocationCustomCreator locationCustomCreator;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdsOptionRepository adsOptionRepositry;

    int numberOfcreatedEvents = 0;

    public LaunchedEvent getLaunchedEvent() {
        this.numberOfcreatedEvents++;

        Organizer customOrganizer = (Organizer) this.userCustomCreator.getUser(Role.ORGANIZER);
        userRepository.save(customOrganizer);
        AdsOption customAdsOption = this.adsOptionCustomCreator.getAdsOption();
        this.adsOptionRepositry.save(customAdsOption);

        LaunchedEvent event = LaunchedEvent.builder()
                .eventAds(customAdsOption)
                .eventLocation(locationCustomCreator.getLocation())
                .name("Event" + numberOfcreatedEvents)
                .eventOrganizer(customOrganizer)
                .description("description" + numberOfcreatedEvents)
                .seatTypes(new ArrayList<>())
                .eventDate(DateFunctions.getCurrentDate())
                .eventType(EventType.LAUNCHEDEVENT)
                .build();
        return event;
    }

    public DraftedEvent getDraftedEvent() {
        this.numberOfcreatedEvents++;

        Organizer customOrganizer = (Organizer) this.userCustomCreator.getUser(Role.ORGANIZER);
        userRepository.save(customOrganizer);
        AdsOption customAdsOption = this.adsOptionCustomCreator.getAdsOption();
        this.adsOptionRepositry.save(customAdsOption);

        DraftedEvent event = DraftedEvent.builder()
                .eventAds(customAdsOption)
                .eventLocation(locationCustomCreator.getLocation())
                .name("Event" + numberOfcreatedEvents)
                .eventOrganizer(customOrganizer)
                .description("description" + numberOfcreatedEvents)
                .seatTypes(new ArrayList<>())
                .eventDate(DateFunctions.getCurrentDate())
                .eventType(EventType.DRAFTEDEVENT)
                .build();
        return event;
    }

}
