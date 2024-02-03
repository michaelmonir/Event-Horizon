package com.EventHorizon.EventHorizon.Services.EventServices.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Exceptions.User.UserNotFoundException;
import com.EventHorizon.EventHorizon.Mappers.Event.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
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
            return eventViewDtoMapper.getDetailedDtoFromEvent(event);
        }
    }
}
