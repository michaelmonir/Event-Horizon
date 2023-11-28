package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.SponsorNotFoundException;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SponsorService {

    @Autowired
    SponsorRepository sponsorRepository;


    public void add(Sponsor sponsor) {
        sponsorRepository.save(sponsor);
    }

    public void delete(int id) {
        Optional<Sponsor> sponsor = sponsorRepository.findById(id);
        if (sponsor.isPresent()) {
            sponsorRepository.deleteById(id);
        } else {
            throw new SponsorNotFoundException();
        }
    }


    public Sponsor getByID(int id) {
        Optional<Sponsor> sponsor = sponsorRepository.findById(id);
        if (sponsor.isPresent()) {
            return sponsor.get();
        } else {
            throw new SponsorNotFoundException();
        }
    }


    public Sponsor getByInformation(Information information) {
        Optional<Sponsor> sponsor = Optional.ofNullable(sponsorRepository.findByInformation(information));
        if (sponsor.isPresent()) {
            return sponsor.get();
        } else {
            throw new SponsorNotFoundException();
        }
    }
}
