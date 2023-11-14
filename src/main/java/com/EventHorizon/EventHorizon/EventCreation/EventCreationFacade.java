package com.EventHorizon.EventHorizon.EventCreation;

import com.EventHorizon.EventHorizon.EventCreation.EventDto.EventDTO;
import com.EventHorizon.EventHorizon.EventCreation.EventService.EventRepositoryService;
import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;
import org.springframework.beans.factory.annotation.Autowired;

public class EventCreationFacade {
    private Organizable organizer;
    @Autowired
    private EventRepositoryService eventRepositoryService;

    public void updateEvent(int id, EventDTO eventDTO) {
        Event event = eventDTO.getEvent();
        this.eventRepositoryService.updateEvent(id, event);
    }///////////////// handle try catch in the service api: case event doesn't exist
}
