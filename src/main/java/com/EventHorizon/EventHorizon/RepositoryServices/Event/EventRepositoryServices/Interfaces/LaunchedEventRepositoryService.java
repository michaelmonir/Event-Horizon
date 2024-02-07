package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces;

import com.EventHorizon.EventHorizon.Entities.Event.LaunchedEvent;

public interface LaunchedEventRepositoryService {
    LaunchedEvent getById(int id);
    LaunchedEvent saveWhenLaunching(LaunchedEvent event);
    void delete(int id);
}
