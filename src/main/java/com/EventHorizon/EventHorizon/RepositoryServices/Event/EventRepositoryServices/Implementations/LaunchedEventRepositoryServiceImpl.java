package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventAlreadyExisting;
import com.EventHorizon.EventHorizon.Exceptions.Event.NotLaunchedEventException;
import com.EventHorizon.EventHorizon.Repository.Event.EventRepository;
import com.EventHorizon.EventHorizon.Entities.Event.EventWrapper.FutureEventWrapper;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.UtilityClasses.DateFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LaunchedEventRepositoryServiceImpl implements SuperEventRepositoryService, LaunchedEventRepositoryService {
    @Autowired
    private CommonEventRepositoryService commonEventRepositoryService;
    @Autowired
    private EventRepository eventRepository;

    @Override
    @Transactional
    public LaunchedEvent saveWhenLaunching(LaunchedEvent event) {
        event.setLaunchedDate(DateFunctions.getCurrentDate());
        if (event.getId() != 0)
            throw new EventAlreadyExisting();
        new FutureEventWrapper(event);
        commonEventRepositoryService.save(event);
        return event;
    }

    @Override
    public void delete(int id) {
        this.checkAndHandleWrongType(id);
        eventRepository.deleteById(id);
    }

    @Override
    public LaunchedEvent update(Event newEvent) {
        this.checkAndHandleWrongType(newEvent.getId());
        new FutureEventWrapper((LaunchedEvent) newEvent);
        commonEventRepositoryService.save(newEvent);
        return (LaunchedEvent) newEvent;
    }

    public List<SeatType> getSeatTypeById(int id) {
        LaunchedEvent launchedEvent = this.getById(id);
        return launchedEvent.getSeatTypes();
    }

    @Override
    public LaunchedEvent getById(int id) {
        Event event = commonEventRepositoryService.getByIdAndHandleNotFound(id);
        if (event.getEventType() != EventType.LAUNCHEDEVENT)
            throw new NotLaunchedEventException();
        return (LaunchedEvent) event;
    }

    private void checkAndHandleWrongType(int id) {
        Event event = commonEventRepositoryService.getByIdAndHandleNotFound(id);
        if (event.getEventType() != EventType.LAUNCHEDEVENT)
            throw new NotLaunchedEventException();
    }
}

