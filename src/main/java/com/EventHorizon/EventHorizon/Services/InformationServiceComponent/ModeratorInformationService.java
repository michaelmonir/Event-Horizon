package com.EventHorizon.EventHorizon.Services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Repository.ModeratorRepository;
import com.EventHorizon.EventHorizon.Services.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class ModeratorInformationService implements UserInformationService{
    @Autowired
    ModeratorService moderatorService;

    @Override
    public void add(Information information) {
        Moderator m1 = Moderator.builder().information(information).build();
        moderatorService.add(m1);
    }

    @Override
    public void delete(Information information) {
        Moderator moderator = moderatorService.getByInformation(information);
        moderatorService.delete(moderator.getId());
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Moderator moderator = moderatorService.getByInformation(information);
        moderator.setInformation(updateInformationDTO.toInformation(information));
        moderatorService.add(moderator);
        return (moderator.getInformation());
    }
}
