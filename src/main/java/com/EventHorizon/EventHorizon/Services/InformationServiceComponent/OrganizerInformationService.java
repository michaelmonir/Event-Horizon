package com.EventHorizon.EventHorizon.Services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.Services.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class OrganizerInformationService implements UserInformationService {

    @Autowired
    OrganizerService organizerService;

    @Override
    public void add(Information information) {
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);
    }

    @Override
    public void delete(Information information) {
        Organizer organizer = organizerService.getByInformation(information);
        organizerService.delete(organizer.getId());
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Organizer organizer = organizerService.getByInformation(information);
        organizer.setInformation(updateInformationDTO.toInformation(information));
        organizerService.add(organizer);
        return (organizer.getInformation());
    }
}
