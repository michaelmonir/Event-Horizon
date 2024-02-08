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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class AdminViewTest {

    @Autowired
    private EventViewService eventViewService;
    @Autowired
    private EventCustomCreator eventCustomCreator;
    @Autowired
    private UserCustomCreator userCustomCreator;

    @ParameterizedTest
    @MethodSource("provideRoles")
    public void launchedEvent(Role role) {
        Event event = eventCustomCreator.getandSaveEvent(EventType.LAUNCHEDEVENT);
        User user = userCustomCreator.getAndSaveUser(role);
        EventViewDto eventViewDto = eventViewService.getEventViewDto(event.getId(), user.getId());
        Assertions.assertEquals(eventViewDto.getUserEventRole(), UserEventRole.ADMIN);
    }

    @ParameterizedTest
    @MethodSource("provideRoles")
    public void draftedEvent(Role role) {
        Event event = eventCustomCreator.getandSaveEvent(EventType.DRAFTEDEVENT);
        User user = userCustomCreator.getAndSaveUser(role);
        Assertions.assertThrows(InvalidAccessOfEventException.class,
                () -> eventViewService.getEventViewDto(event.getId(), user.getId()) );
    }

    private static Stream<Arguments> provideRoles() {
        return Stream.of(
                Arguments.of(Role.ADMIN),
                Arguments.of(Role.MODERATOR)
        );
    }
}
