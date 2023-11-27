package com.EventHorizon.EventHorizon.RepositoryServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositry;
import com.EventHorizon.EventHorizon.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationRepositoryService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private EventRepositry eventRepositry;

    public void deleteLocationById(int id) {
        Optional<Event> optionalEvent = eventRepositry.findById(id);

        if (!optionalEvent.isPresent())
            throw new EventNotFoundException();

        optionalEvent.get().setEventLocation(null);
        locationRepository.deleteLocationByEventId(id);
    }
}
