package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class ModeratorInformationService implements UserInformationService{
    @Autowired
    ModeratorRepository moderatorRepository;

    @Override
    public void add(Information information) {
        Moderator m1 = moderatorRepository.findByInformation(information);
        moderatorRepository.delete(m1);
    }

    @Override
    public void delete(Information information) {
        Moderator moderator = moderatorRepository.findByInformation(information);
        moderatorRepository.delete(moderator);
    }
}
