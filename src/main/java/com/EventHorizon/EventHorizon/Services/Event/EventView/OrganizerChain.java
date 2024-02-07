package com.EventHorizon.EventHorizon.Services.Event.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.User.User;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.Services.Event.UserEventService;
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
    private ClientChain clientChain;

    public EventViewDto getDto(User user, Event event) {
        try {
            userEventService.checkOrganizerOfEvent(user, event);
            return eventViewDtoMapper.getDetailedDtoFromEvent(event, UserEventRole.ORGANIZER);
        }
        catch (NotOrganizerOfThisEventException e) {
            return clientChain.getDto(user, event);
        }
    }
}
