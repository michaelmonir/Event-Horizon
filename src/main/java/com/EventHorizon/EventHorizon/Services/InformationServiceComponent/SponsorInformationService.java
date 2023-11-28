package com.EventHorizon.EventHorizon.Services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Repository.SponsorRepository;
import com.EventHorizon.EventHorizon.Services.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SponsorInformationService implements UserInformationService {
    @Autowired
    SponsorService sponsorService;

    @Override
    public void add(Information information) {
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);
    }

    @Override
    public void delete(Information information) {
        Sponsor sponsor = sponsorService.getByInformation(information);
        sponsorService.delete(sponsor.getId());
    }

    @Override
    public Information update(UpdateInformationDTO updateInformationDTO, Information information) {
        Sponsor sponsor = sponsorService.getByInformation(information);
        sponsor.setInformation(updateInformationDTO.toInformation(information));
        sponsorService.add(sponsor);
        return (sponsor.getInformation());
    }

}
