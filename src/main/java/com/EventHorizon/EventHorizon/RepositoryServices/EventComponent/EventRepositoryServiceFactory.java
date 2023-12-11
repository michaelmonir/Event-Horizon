package com.EventHorizon.EventHorizon.RepositoryServices.EventComponent;

import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventRepositoryServiceFactory {
    @Autowired
    private LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    private DraftedEventRepositoryService draftedEventRepositoryService;

    public SuperEventRepositoryService getEventRepositoryServiceByEventType(EventType eventType){
        if(Objects.equals(eventType, EventType.LAUNCHEDEVENT))
            return launchedEventRepositoryService;
        else return draftedEventRepositoryService;
    }
}
