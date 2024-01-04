package com.EventHorizon.EventHorizon.Services.EventServices.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.EventType;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.Mappers.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnregisteredUserChain {

    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;
    @Autowired
    private InformationRepositoryService informationRepositoryService;
    @Autowired
    private AdminChain adminChain;


    public EventViewDto getDto(Event event, int userInformationId) {
        try {
            Information information = informationRepositoryService.getByID(userInformationId);
            return adminChain.getDto(information, event);
        } catch (InformationNotFoundException e) {
            if (event.getEventType() == EventType.DRAFTEDEVENT)
                throw new InvalidAccessOfEventException();
            return eventViewDtoMapper.getDetailedDtoFromEvent(event);
        }
    }
}
