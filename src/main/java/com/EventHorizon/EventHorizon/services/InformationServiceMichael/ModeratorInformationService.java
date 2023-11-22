package com.EventHorizon.EventHorizon.services.InformationServiceMichael;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ModeratorInformationService implements UserInformationService
{
    @Autowired
    ModeratorRepository moderatorRepository;

    @Override
    public void add(Information information, boolean addUser)
    {
        Moderator m1 = moderatorRepository.findByInformation(information);
        moderatorRepository.delete(m1);
    }

    @Override
    public void delete(int id, Information information)
    {
        Moderator moderator = moderatorRepository.findByInformation(information);
        moderatorRepository.delete(moderator);
    }
}
