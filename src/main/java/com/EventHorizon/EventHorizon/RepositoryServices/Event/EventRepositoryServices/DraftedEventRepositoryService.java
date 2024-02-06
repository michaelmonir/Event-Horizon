package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions.NotDraftedEventException;
import com.EventHorizon.EventHorizon.Repository.Event.DraftedEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DraftedEventRepositoryService implements SuperEventRepositoryService {
    @Autowired
    private DraftedEventRepository draftedEventRepository;
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private EventAndSeatTypeAndSeatArchiveRepositoryService savingRepositoryService;

    public DraftedEvent saveWhenCreating(DraftedEvent event) {
        if (event.getId() != 0)
            throw new EventAlreadyExisting();

        this.savingRepositoryService.saveEventAndSeatTypeAndSeatArchive(event);
        return event;
    }

    public DraftedEvent update(Event event) {
        eventRepositoryService.checkExistingEvent(event.getId());
        this.savingRepositoryService.saveEventAndSeatTypeAndSeatArchive((DraftedEvent)event);
        return (DraftedEvent) event;
    }

    public DraftedEvent getByIdAndHandleNotFound(int id) {
        Event event = eventRepositoryService.getByIdAndHandleNotFound(id);
        if (event.getEventType() != EventType.DRAFTEDEVENT)
            throw new NotDraftedEventException();
        return (DraftedEvent) event;
    }
}
