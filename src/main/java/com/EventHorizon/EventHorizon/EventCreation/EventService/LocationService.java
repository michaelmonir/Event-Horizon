package com.EventHorizon.EventHorizon.EventCreation.EventService;

import com.EventHorizon.EventHorizon.EventCreation.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import com.EventHorizon.EventHorizon.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private EventRepositry eventRepositry;
    public void deleteLocationById(int id){
        Optional<Event> optionalEvent=eventRepositry.findById(id);

        if(!optionalEvent.isPresent())
            throw new EventNotFoundException();

        optionalEvent.get().setEventLocation(null);
        locationRepository.deleteLocationByEventId(id);
    }
}
