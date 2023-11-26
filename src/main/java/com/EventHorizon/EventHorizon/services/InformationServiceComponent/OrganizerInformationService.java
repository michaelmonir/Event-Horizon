package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class OrganizerInformationService implements UserInformationService{

    @Autowired
    OrganizerRepository organizerRepository;

    @Override
    public void add(Information information)
    {
        Organizer organizer = organizerRepository.findByInformation(information);
        organizerRepository.delete(organizer);
    }
    @Override
    public void delete( Information information)
    {
        Organizer organizer = organizerRepository.findByInformation(information);
        organizerRepository.delete(organizer);
    }

}
