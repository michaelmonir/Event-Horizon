package com.EventHorizon.EventHorizon.EntityCustomCreators.Event;

import com.EventHorizon.EventHorizon.Entities.Event.AdsOption;
import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.Event.EventBuilder;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.User.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.Event.NullEventTypeException;
import com.EventHorizon.EventHorizon.Repository.Event.AdsOptionRepository;
import com.EventHorizon.EventHorizon.Repository.Event.EventRepository;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations.EventAndSeatTypeAndSeatArchiveRepositoryService;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private EventAndSeatTypeAndSeatArchiveRepositoryService savingEventService;

    int numberOfcreatedEvents = 0;

    public Event getEvent(EventType eventType) {
        this.numberOfcreatedEvents++;

        Organizer customOrganizer = (Organizer) this.userCustomCreator.getAndSaveUser(Role.ORGANIZER);
        userRepository.save(customOrganizer);
        AdsOption customAdsOption = this.adsOptionCustomCreator.getAdsOption();
        this.adsOptionRepositry.save(customAdsOption);

        Event event = this.getEventBuilder(eventType)
                .eventAds(customAdsOption)
                .eventLocation(locationCustomCreator.getLocation())
                .name("Event" + numberOfcreatedEvents)
                .eventOrganizer(customOrganizer)
                .description("description" + numberOfcreatedEvents)
                .seatTypes(List.of(new SeatType("SeatType1", 1, 10000)))
                .eventDate(DateFunctions.getCurrentDate())
                .eventType(eventType)
                .build();
        return event;
    }

    public Event getAndSaveEvent(EventType eventType) {
        Event event = this.getEvent(eventType);
        this.saveEvent(event);
        return event;
    }

    public Event getEvent(EventType eventType, List<SeatType> seatTypeList) {
        Event event = this.getEvent(eventType);
        event.setSeatTypes(seatTypeList);
        return event;
    }

    public Event getAndSaveEvent(EventType eventType, List<SeatType> seatTypeList) {
        Event event = this.getEvent(eventType, seatTypeList);
        this.saveEvent(event);
        return event;
    }

    public void saveEvent(Event event) {
        savingEventService.saveEventAndSeatTypeAndSeatArchive(event);
        this.eventRepository.save(event);
    }

    public Event getEventWithoutSeatTypes(EventType eventType) {
        Event event = this.getEvent(eventType);
        event.setSeatTypes(new ArrayList<>());
        return event;
    }

    private EventBuilder getEventBuilder(EventType eventType) {
        if (eventType == EventType.LAUNCHEDEVENT)
            return LaunchedEvent.builder();
        else if (eventType == EventType.DRAFTEDEVENT)
            return DraftedEvent.builder();
        else
            throw new NullEventTypeException();
    }
}
