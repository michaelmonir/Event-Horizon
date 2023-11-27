package com.EventHorizon.EventHorizon.Services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class OrganizerInformationService implements UserInformationService{

    @Autowired
    OrganizerRepository organizerRepository;

    @Override
    public void add(Information information)
    {
        Organizer organizer = Organizer.builder().information(information).build();
        organizerRepository.save(organizer);
    }
    @Override
    public void delete( Information information)
    {
        Organizer organizer = organizerRepository.findByInformation(information);
        organizerRepository.delete(organizer);
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Organizer organizer = organizerRepository.findByInformation(information);
        organizer.setInformation(updateInformationDTO.toInformation(information));
        organizerRepository.save(organizer);
        return  (organizer.getInformation());
    }


}
