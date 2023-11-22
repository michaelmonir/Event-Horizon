package com.EventHorizon.EventHorizon.services.InformationServiceMichael;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.services.InformationServiceModified.InformationService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrganizerInformationService implements UserInformationService
{
    @Autowired
    OrganizerRepository organizerRepository;

    @Override
    public void add(Information information, boolean addUser)
    {
        Organizer organizer = organizerRepository.findByInformation(information);
        organizerRepository.delete(organizer);
    }

    @Override
    public void delete(int id, Information information)
    {
        Organizer organizer = organizerRepository.findByInformation(information);
        organizerRepository.delete(organizer);
    }
}
