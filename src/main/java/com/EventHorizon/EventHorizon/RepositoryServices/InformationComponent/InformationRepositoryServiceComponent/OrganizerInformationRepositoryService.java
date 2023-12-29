package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.OrganizerNotFoundException;
import com.EventHorizon.EventHorizon.Repository.UserRepositories.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OrganizerInformationRepositoryService implements UserInformationRepositoryService {

    @Autowired
    OrganizerRepository organizerRepository;

    @Override
    public User add(Information information) {
        Organizer organizer = Organizer.builder().information(information).build();
        organizerRepository.save(organizer);
        return organizer;
    }

    @Override
    public void delete(Information information) {
        Organizer organizer = (Organizer)this.getUserByInformation(information);

        organizerRepository.deleteById(organizer.getId());
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Organizer organizer = (Organizer)this.getUserByInformation(information);

        Information newInformation = updateInformationDTO.toInformation(information);
        organizer.setInformation(newInformation);
        organizerRepository.save(organizer);

        return newInformation;
    }

    public User getUserByInformation(Information information){
        Optional<Organizer> organizer = Optional.ofNullable(organizerRepository.findByInformation(information));
        if (!organizer.isPresent())
            throw new OrganizerNotFoundException();
        return organizer.get();
    }

    @Override
    public List<? extends User> findAllOfUsers() {
        return  organizerRepository.findAll();
    }
}
