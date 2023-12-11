package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.EventEntities.AdsOption;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Repository.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
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
    private EventRepositry eventRepositry;
    @Autowired
    private OrganizerRepository organizerRepository;
    @Autowired
    private AdsOptionRepository adsOptionRepositry;


    int numberOfcreatedEvents = 0;

    public Event getEvent()
    {
        this.numberOfcreatedEvents++;

        Organizer customOrganizer = (Organizer) this.userCustomCreator.getUserAndSaveInRepository(Role.ORGANIZER);
        organizerRepository.save(customOrganizer);
        AdsOption customAdsOption = this.adsOptionCustomCreator.getAdsOption();
        this.adsOptionRepositry.save(customAdsOption);

        Event event = Event.builder()
                .eventAds(customAdsOption)
                .eventLocation(locationCustomCreator.getLocation())
                .name("Event" + numberOfcreatedEvents)
                .eventOrganizer(customOrganizer)
                .description("description" + numberOfcreatedEvents)
                .seatTypes(new ArrayList<>())
                .build();
        return event;
    }
}
