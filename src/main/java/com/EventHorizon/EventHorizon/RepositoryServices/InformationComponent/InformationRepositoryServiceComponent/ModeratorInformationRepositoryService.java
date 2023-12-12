package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ModeratorNotFoundException;
import com.EventHorizon.EventHorizon.Repository.ModeratorRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.UserInformationRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ModeratorInformationRepositoryService implements UserInformationRepositoryService {
    @Autowired
    ModeratorRepository moderatorRepository;

    @Override
    public User add(Information information) {
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorRepository.save(moderator);
        return moderator;
    }

    @Override
    public void delete(Information information) {
        Moderator moderator = (Moderator)this.getUserByInformation(information);

        moderatorRepository.deleteById(moderator.getId());
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Moderator moderator = (Moderator)this.getUserByInformation(information);

        Information newInformation = updateInformationDTO.toInformation(information);
        moderator.setInformation(newInformation);
        moderatorRepository.save(moderator);

        return newInformation;
    }

    public User getUserByInformation(Information information){
        Optional<Moderator> moderator = Optional.ofNullable(moderatorRepository.findByInformation(information));
        if (!moderator.isPresent())
            throw new ModeratorNotFoundException();
        return moderator.get();
    }

    @Override
    public List<? extends User> findAllOfUsers() {
        return moderatorRepository.findAll();
    }
}
