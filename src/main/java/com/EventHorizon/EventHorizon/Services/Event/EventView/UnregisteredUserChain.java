package com.EventHorizon.EventHorizon.Services.Event.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.Event.Event;
import com.EventHorizon.EventHorizon.Entities.User.User;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Exceptions.User.UserNotFoundException;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnregisteredUserChain {

    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;
    @Autowired
    private AdminChain adminChain;
    @Autowired
    private GetUserRepositoryService getUserRepositoryService;

    public EventViewDto getDto(Event event, int userId) {
        try {
            User user = getUserRepositoryService.getById(userId);
            return adminChain.getDto(user, event);
        } catch (UserNotFoundException e) {
            if (event.getEventType() == EventType.DRAFTEDEVENT)
                throw new InvalidAccessOfEventException();
            return eventViewDtoMapper.getDetailedDtoFromEvent(event, UserEventRole.VIEWONLY);
        }
    }
}
