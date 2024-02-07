package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces;

import com.EventHorizon.EventHorizon.Entities.Event.DraftedEvent;

public interface DraftedEventRepositoryService {
    DraftedEvent getById(int id);
    DraftedEvent saveWhenCreating(DraftedEvent event);
}
