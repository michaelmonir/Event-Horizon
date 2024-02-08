package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Implementations;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.Event.NullEventTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRepositoryServiceFactory {
    @Autowired
    private LaunchedEventRepositoryServiceImpl launchedEventRepositoryServiceImpl;
    @Autowired
    private DraftedEventRepositoryServiceImpl draftedEventRepositoryServiceImpl;

    SuperEventRepositoryService getByEventType(EventType eventType){
        if(eventType == EventType.LAUNCHEDEVENT)
            return launchedEventRepositoryServiceImpl;
        else if (eventType == EventType.DRAFTEDEVENT)
            return draftedEventRepositoryServiceImpl;
        else
            throw new NullEventTypeException();
    }
}
