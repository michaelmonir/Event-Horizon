package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.EventEntities.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.EventEntities.SuperEvent;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventNotFoundException;
import com.EventHorizon.EventHorizon.Repository.DraftedEventRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.Mappers.AdsOptionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DraftedEventRepositoryService implements SuperEventRepositoryService {

    @Autowired
    private DraftedEventRepository draftedEventRepository;


    public DraftedEvent saveEventWhenCreatingAndHandleAlreadyExisting(SuperEvent event) {
        DraftedEvent draftedEvent = (DraftedEvent) event;
        if (draftedEvent.getId() != 0)
            throw new EventAlreadyExisting();

        draftedEventRepository.save(draftedEvent);
        return draftedEvent;
    }

    public DraftedEvent updateEventAndHandleNotFound(SuperEvent event) {
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

    public DraftedEvent setEventOrganizer(Organizer organizer, SuperEvent superEvent) {
        DraftedEvent draftedEvent = (DraftedEvent) superEvent;
        draftedEvent.setEventOrganizer(organizer);
        return draftedEvent;
    }

    public Event getEventFromSuperEvent(SuperEvent superEvent) {
        DraftedEvent draftedEvent = (DraftedEvent) superEvent;
        return draftedEvent.getEvent();
    }
    public DraftedEvent getEventAndHandleNotFound(int id) {
        Optional<DraftedEvent> optionalOldEvent = draftedEventRepository.findById(id);
        if (optionalOldEvent.isEmpty())
            throw new EventNotFoundException();
        return optionalOldEvent.get();
    }


}
