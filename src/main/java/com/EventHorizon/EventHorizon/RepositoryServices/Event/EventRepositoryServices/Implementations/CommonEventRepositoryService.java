package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.Event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonEventRepositoryService {

    @Autowired
    private EventRepository eventRepository;

    Event getByIdAndHandleNotFound(int id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isEmpty())
            throw new EventNotFoundException();
        return eventOptional.get();
    }

    void delete(int id) {
        this.checkExistingEvent(id);
        eventRepository.deleteById(id);
    }

    void checkExistingEvent(int id) {
        if (!eventRepository.existsById(id))
            throw new EventNotFoundException();
    }
}
