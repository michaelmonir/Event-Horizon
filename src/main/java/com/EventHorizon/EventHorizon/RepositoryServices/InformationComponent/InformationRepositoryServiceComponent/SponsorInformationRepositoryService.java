package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.SponsorNotFoundException;
import com.EventHorizon.EventHorizon.Repository.SponsorRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.UserInformationRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorInformationRepositoryService implements UserInformationRepositoryService {
    @Autowired
    SponsorRepository sponsorRepository;

    @Override
    public void add(Information information) {
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorRepository.save(sponsor);
    }

    @Override
    public void delete(Information information) {
        Sponsor sponsor = (Sponsor) this.getUserByInformation(information);

        sponsorRepository.deleteById(sponsor.getId());
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Sponsor sponsor = (Sponsor) this.getUserByInformation(information);

        Information newInformation = updateInformationDTO.toInformation(information);
        sponsor.setInformation(newInformation);
        sponsorRepository.save(sponsor);

        return newInformation;
    }

    public User getUserByInformation(Information information) {
        Optional<Sponsor> sponsor = Optional.ofNullable(sponsorRepository.findByInformation(information));
        if (!sponsor.isPresent())
            throw new SponsorNotFoundException();
        return sponsor.get();
    }

    @Override
    public List<? extends User> findAllOfUsers() {
        return sponsorRepository.findAll();
    }
}
