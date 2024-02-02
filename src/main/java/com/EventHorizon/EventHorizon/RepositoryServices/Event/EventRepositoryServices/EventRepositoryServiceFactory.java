package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NullEventTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRepositoryServiceFactory {
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;

    public SuperEventRepositoryService getByEventType(EventType eventType){
        if(eventType == EventType.LAUNCHEDEVENT)
            return launchedEventRepositoryService;
        else if (eventType == EventType.DRAFTEDEVENT)
            return draftedEventRepositoryService;
        else
            throw new NullEventTypeException();
    }
}
