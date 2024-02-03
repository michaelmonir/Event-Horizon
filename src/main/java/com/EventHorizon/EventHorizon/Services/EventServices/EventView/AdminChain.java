package com.EventHorizon.EventHorizon.Services.EventServices.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminChain {
    @Autowired
    private OrganizerChain organizerChain;
    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;

    public EventViewDto getDto(User user, Event event) {
        if (user.getRole() != Role.ADMIN)
            return organizerChain.getDto(user, event);

        if (event.getEventType() == EventType.DRAFTEDEVENT)
            throw new InvalidAccessOfEventException();

        return eventViewDtoMapper.getDetailedDtoFromEvent(event, UserEventRole.ADMIN);
    }
}