package com.EventHorizon.EventHorizon.RepositoryServices.Location;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationRepositoryService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private EventRepository eventRepository;

    public void deleteLocationById(int id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);

        if (!optionalEvent.isPresent())
            throw new EventNotFoundException();

        optionalEvent.get().setEventLocation(null);
        locationRepository.deleteLocationByEventId(id);
    }
}
