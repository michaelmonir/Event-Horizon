package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTO.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTO.ViewInformationDTO;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.entity.User;
import com.EventHorizon.EventHorizon.repository.InformationRepository;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
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
