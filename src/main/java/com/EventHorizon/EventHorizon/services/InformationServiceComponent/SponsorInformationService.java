package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SponsorInformationService implements UserInformationService {
    @Autowired
    SponsorRepository sponsorRepository;

    @Override
    public void add(Information information) {
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorRepository.save(sponsor);
    }

    @Override
    public void delete(Information information) {
        Sponsor sponsor = sponsorRepository.findByInformation(information);
        sponsorRepository.delete(sponsor);
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Sponsor sponsor = sponsorRepository.findByInformation(information);
        sponsor.setInformation(updateInformationDTO.toInformation(information));
        sponsorRepository.save(sponsor);
        return (sponsor.getInformation());
    }

}
