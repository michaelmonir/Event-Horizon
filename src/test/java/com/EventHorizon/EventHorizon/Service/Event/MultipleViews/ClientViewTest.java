package com.EventHorizon.EventHorizon.Service.Event.MultipleViews;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.User.User;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.Event.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Services.Event.EventView.EventViewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientViewTest {
    @Autowired
    private EventViewService eventViewService;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private UserCustomCreator userCustomCreator;

    @Test
    public void launchedEvent() {
        Event event = eventCustomCreator.getandSaveEvent(EventType.LAUNCHEDEVENT);
        User user = userCustomCreator.getAndSaveUser(Role.CLIENT);
        EventViewDto eventViewDto = eventViewService.getEventViewDto(event.getId(), user.getId());
        Assertions.assertEquals(eventViewDto.getUserEventRole(), UserEventRole.CLIENT);
    }

    @Test
    public void draftedEvent() {
        Event event = eventCustomCreator.getandSaveEvent(EventType.DRAFTEDEVENT);
        User user = userCustomCreator.getAndSaveUser(Role.CLIENT);
        Assertions.assertThrows(InvalidAccessOfEventException.class,
                () -> eventViewService.getEventViewDto(event.getId(), user.getId()) );
    }
}
