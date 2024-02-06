package com.EventHorizon.EventHorizon.Service.Event.MultipleViews;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.Services.EventServices.EventView.EventViewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrganizerViewTest {

    @Autowired
    private EventViewService eventViewService;
    @Autowired
    private EventCustomCreator eventCustomCreator;

    @Test
    public void launchedEvent() {
        Event event = eventCustomCreator.getandSaveEvent(EventType.LAUNCHEDEVENT);
        EventViewDto eventViewDto = eventViewService.getEventViewDto(event.getId(), event.getEventOrganizer().getId());
        Assertions.assertEquals(eventViewDto.getUserEventRole(), UserEventRole.ORGANIZER);
    }

    @Test
    public void draftedEvent() {
        Event event = eventCustomCreator.getandSaveEvent(EventType.DRAFTEDEVENT);
        EventViewDto eventViewDto = eventViewService.getEventViewDto(event.getId(), event.getEventOrganizer().getId());
        Assertions.assertEquals(eventViewDto.getUserEventRole(), UserEventRole.ORGANIZER);
    }
}
