package com.EventHorizon.EventHorizon.Services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Repository.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class ModeratorInformationService implements UserInformationService{
    @Autowired
    ModeratorRepository moderatorRepository;

    @Override
    public void add(Information information) {
        Moderator m1 = Moderator.builder().information(information).build();
        moderatorRepository.save(m1);
    }

    @Override
    public void delete(Information information) {
        Moderator moderator = moderatorRepository.findByInformation(information);
        moderatorRepository.delete(moderator);
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Moderator moderator = moderatorRepository.findByInformation(information);
        moderator.setInformation(updateInformationDTO.toInformation(information));
        moderatorRepository.save(moderator);
        return (moderator.getInformation());
    }
}
