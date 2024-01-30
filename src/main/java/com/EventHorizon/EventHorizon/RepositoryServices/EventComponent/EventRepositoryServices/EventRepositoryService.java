package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.EventRepositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventRepositoryService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;

    public Event getById(int id){
        try {
            return launchedEventRepositoryService.getByIdAndHandleNotFound(id);
        } catch (EventNotFoundException e) {
            return draftedEventRepositoryService.getByIdAndHandleNotFound(id);
        }
//        Optional<Event> eventOptional = eventRepository.findById(id);
//        if (eventOptional.isEmpty())
//            throw new EventNotFoundException();
//        return eventOptional.get();
    }
}
