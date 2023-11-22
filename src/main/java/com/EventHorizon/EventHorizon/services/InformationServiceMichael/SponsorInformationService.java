package com.EventHorizon.EventHorizon.services.InformationServiceMichael;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.entity.Sponsor;
import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.repository.SponsorRepository;
import com.EventHorizon.EventHorizon.services.InformationServiceModified.InformationService;
import org.springframework.beans.factory.annotation.Autowired;

public class SponsorInformationService implements UserInformationService
{
    @Autowired
    SponsorRepository sponsorRepository;

    @Override
    public void add(Information information, boolean addUser)
    {
        Sponsor sponsor = sponsorRepository.findByInformation(information);
        sponsorRepository.delete(sponsor);
    }

    @Override
    public void delete(int id, Information information)
    {
        Sponsor sponsor = sponsorRepository.findByInformation(information);
        sponsorRepository.delete(sponsor);
    }
}
