package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.DraftedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DraftedEventRepositoryService implements SuperEventRepositoryService {

    @Autowired
    private DraftedEventRepository draftedEventRepository;


    public DraftedEvent saveEventWhenCreatingAndHandleAlreadyExisting(Event event) {
        DraftedEvent draftedEvent = (DraftedEvent) event;
        if (draftedEvent.getId() != 0)
            throw new EventAlreadyExisting();

        draftedEventRepository.save(draftedEvent);
        return draftedEvent;
    }

    public DraftedEvent updateEventAndHandleNotFound(Event event) {
        DraftedEvent draftedEvent = (DraftedEvent) event;
        int id = draftedEvent.getId();
        getEventAndHandleNotFound(id);
        draftedEvent.setId(id);
        draftedEventRepository.save(draftedEvent);
        return draftedEvent;
    }

    public void deleteEvent(int id) {
        getEventAndHandleNotFound(id);
        draftedEventRepository.deleteById(id);
    }

    public DraftedEvent getEventAndHandleNotFound(int id) {
        Optional<DraftedEvent> optionalOldEvent = draftedEventRepository.findById(id);
        if (optionalOldEvent.isEmpty())
            throw new EventNotFoundException();
        return optionalOldEvent.get();
    }
}
