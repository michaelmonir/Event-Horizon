package com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices;

import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.Event.EventNotFoundException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.Interfaces.EventRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventRepositoryServiceTest {
    @Autowired
    private EventRepositoryService eventRepositoryService;
    @Autowired
    private EventCustomCreator eventCustomCreator;

    @ParameterizedTest
    @EnumSource(value = EventType.class , names = {"LAUNCHEDEVENT", "DRAFTEDEVENT"})
    public void getById(EventType eventType) {
        Event event = eventCustomCreator.getandSaveEvent(eventType);
        Event eventFromDb = eventRepositoryService.getById(event.getId());

        Assertions.assertEquals(eventFromDb.getEventType(), eventType);
        Assertions.assertEquals(eventFromDb, event);
    }

    @Test
    public void getByIdNotExisting() {
        Assertions.assertThrows(EventNotFoundException.class,
                () -> eventRepositoryService.getById(100007));
    }

    @ParameterizedTest
    @EnumSource(value = EventType.class , names = {"LAUNCHEDEVENT", "DRAFTEDEVENT"})
    public void update(EventType eventType) {
        Event event = eventCustomCreator.getandSaveEvent(eventType);
        event.setName("Updated Name");
        eventRepositoryService.update(event);
        Event eventFromDb = eventRepositoryService.getById(event.getId());
        Assertions.assertEquals(eventFromDb.getName(), "Updated Name");
        Assertions.assertEquals(event, eventFromDb);
    }

    @ParameterizedTest
    @EnumSource(value = EventType.class , names = {"LAUNCHEDEVENT", "DRAFTEDEVENT"})
    public void delete(EventType eventType) {
        Event event = eventCustomCreator.getandSaveEvent(eventType);
        eventRepositoryService.delete(event.getId());
        Assertions.assertThrows(EventNotFoundException.class
                , () -> eventRepositoryService.getById(event.getId()));
    }

    @Test
    public void deleteEventNotExisting() {
        Assertions.assertThrows(EventNotFoundException.class
                , () -> eventRepositoryService.delete(1000007));
    }
}
