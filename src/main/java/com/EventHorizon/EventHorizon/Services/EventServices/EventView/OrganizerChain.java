package com.EventHorizon.EventHorizon.Services.EventServices.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.Services.EventServices.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerChain
{
    @Autowired
    private UserEventService userEventService;
    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;
    @Autowired
    private UserChain userChain;

    public EventViewDto getDto(User user, Event event) {
        if (user.getRole() != Role.ORGANIZER)
            throw new InvalidAccessOfEventException();
        try {
            userEventService.checkOrganizerOfEvent(user, event);
            return eventViewDtoMapper.getDetailedDtoFromEvent(event, UserEventRole.Organizer);
        }
        catch (NotOrganizerOfThisEventException e) {
            return userChain.getDto(event);
        }
    }
}
