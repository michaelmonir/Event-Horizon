package com.EventHorizon.EventHorizon.Services.EventServices.EventView;

import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.EventViewDto;
import com.EventHorizon.EventHorizon.DTOs.EventDto.EventViewDtos.UserEventRole;
import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.InvalidAccessOfEventException;
import com.EventHorizon.EventHorizon.Exceptions.EventExceptions.NotOrganizerOfThisEventException;
import com.EventHorizon.EventHorizon.Mappers.EventViewDtoMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.OrganizerInformationRepositoryService;
import com.EventHorizon.EventHorizon.Services.EventServices.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerChain
{
    @Autowired
    private UserEventService userEventService;
    @Autowired
    private OrganizerInformationRepositoryService organizerInformationRepositoryService;
    @Autowired
    private EventViewDtoMapper eventViewDtoMapper;
    @Autowired
    private UserChain userChain;

    public EventViewDto getDto(Information information, Event event) {
        if (information.getRole() != Role.ORGANIZER)
            throw new InvalidAccessOfEventException();
        Organizer organizer
                = (Organizer) organizerInformationRepositoryService.getUserByInformation(information);
        try {
            userEventService.checkAndHandleNotOrganizerOfEvent(organizer, event);
        }
        catch (NotOrganizerOfThisEventException e) {
            return userChain.getDto(event);
        }
        return eventViewDtoMapper.getDetailedDtoFromEvent(event, UserEventRole.Organizer);
    }
}
