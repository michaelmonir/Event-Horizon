package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTO.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTO.ViewInformationDTO;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Sponsor;
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
