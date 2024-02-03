package com.EventHorizon.EventHorizon.Service.Event.MultipleViews;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.Event.EventCustomCreator;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.EventRepositoryServiceInterface;
import com.EventHorizon.EventHorizon.Services.EventServices.EventView.EventViewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminViewTest {

    @Autowired
    private EventViewService eventViewService;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private EventRepositoryServiceInterface eventRepositoryServiceInterface;
    @Autowired
    private UserCustomCreator userCustomCreator;

    @Test
    public void launchedEvent() {
        Event event = eventCustomCreator.getLaunchedEvent();
        eventRepositoryServiceInterface.create(event);
        User user = userCustomCreator.getAndSaveUser(Role.ADMIN);
        EventViewDto eventViewDto = eventViewService.getEventViewDto(event.getId(), user.getId());
        Assertions.assertEquals(eventViewDto.getUserEventRole(), UserEventRole.ADMIN);
    }

    @Test
    public void draftedEvent() {
        Event event = eventCustomCreator.getDraftedEvent();
        eventRepositoryServiceInterface.create(event);
        User user = userCustomCreator.getAndSaveUser(Role.ADMIN);
        Assertions.assertThrows(InvalidAccessOfEventException.class,
                () -> eventViewService.getEventViewDto(event.getId(), user.getId()) );
    }
}
