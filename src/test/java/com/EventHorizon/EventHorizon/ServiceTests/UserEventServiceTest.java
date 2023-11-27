package com.EventHorizon.EventHorizon.ServiceTests;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.entity.InformationCreator;
import com.EventHorizon.EventHorizon.services.UserEventService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserEventServiceTest {
    @Autowired
    private UserEventService userEventService;
    @Autowired
    private InformationCreator informationCreator;

    @Test
    public void organizerOfEvent() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer = Organizer.builder().information(information).build();
        Event event = Event.builder()
                .eventOrganizer(organizer)
                .build();
        Assertions.assertDoesNotThrow(() -> {
            userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
        });
    }

    @Test
    public void notOrganizerOfEvent() {
        Information information = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer = Organizer.builder().information(information).build();
        Event event = Event.builder()
                .eventOrganizer(organizer)
                .build();
        Information information2 = informationCreator.getInformation("ROLE_ORGANIZER");
        Organizer organizer2 = Organizer.builder().information(information2).build();
        Assertions.assertThrows(NotOrganizerOfThisEventException.class,() -> {
            userEventService.checkAndHandleNotOrganizerOfEvent(organizer2, event);
        });
    }
}
