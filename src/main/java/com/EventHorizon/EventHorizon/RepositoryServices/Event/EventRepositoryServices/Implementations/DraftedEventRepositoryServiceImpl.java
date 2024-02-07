package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions.NotDraftedEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces.DraftedEventRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DraftedEventRepositoryServiceImpl implements SuperEventRepositoryService, DraftedEventRepositoryService {
    @Autowired
    private CommonEventRepositoryService commonEventRepositoryService;
    @Autowired
    private EventAndSeatTypeAndSeatArchiveRepositoryService savingRepositoryService;

    @Override
    public DraftedEvent getById(int id) {
        Event event = commonEventRepositoryService.getByIdAndHandleNotFound(id);
        if (event.getEventType() != EventType.DRAFTEDEVENT)
            throw new NotDraftedEventException();
        return (DraftedEvent) event;
    }

    @Override
    public DraftedEvent saveWhenCreating(DraftedEvent event) {
        if (event.getId() != 0)
            throw new EventAlreadyExisting();

        this.savingRepositoryService.saveEventAndSeatTypeAndSeatArchive(event);
        return event;
    }

    @Override
    public DraftedEvent update(Event event) {
        commonEventRepositoryService.checkExistingEvent(event.getId());
        this.savingRepositoryService.saveEventAndSeatTypeAndSeatArchive(event);
        return (DraftedEvent) event;
    }
}
