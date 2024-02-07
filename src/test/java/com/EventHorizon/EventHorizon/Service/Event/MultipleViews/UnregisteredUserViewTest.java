package com.EventHorizon.EventHorizon.Service.Event.MultipleViews;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Services.Event.EventView.EventViewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UnregisteredUserViewTest {

    @Autowired
    private EventViewService eventViewService;
    @Autowired
    private EventCustomCreator eventCustomCreator;

    @Test
    public void launchedEvent() {
        Event event = eventCustomCreator.getandSaveEvent(EventType.LAUNCHEDEVENT);
        EventViewDto eventViewDto = eventViewService.getEventViewDto(event.getId(), 0);
        Assertions.assertEquals(eventViewDto.getUserEventRole(), UserEventRole.VIEWONLY);
    }

    @Test
    public void draftedEvent() {
        Event event = eventCustomCreator.getandSaveEvent(EventType.DRAFTEDEVENT);
        Assertions.assertThrows(InvalidAccessOfEventException.class, () ->
                eventViewService.getEventViewDto(event.getId(), 0) );
    }
}








