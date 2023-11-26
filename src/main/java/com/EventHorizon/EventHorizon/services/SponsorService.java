package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.Exceptions.AlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.entity.Sponsor;
import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SponsorService {

    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    InformationService informationService;

    public void add(Sponsor sponsor) {
        sponsorRepository.save(sponsor);
    }

    public void delete(int id) {
        Optional<Sponsor> sponsor = sponsorRepository.findById(id);
        if (sponsor.isPresent()) {
            sponsorRepository.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }


    public void update(int id, Sponsor newOne) {
        Optional<Sponsor> old = sponsorRepository.findById(id);
        if (old.isPresent()) {
            Sponsor oldOne = old.get();
            informationService.update(oldOne.getInformation().getId(), newOne.getInformation());
            newOne.setId(oldOne.getId());
            sponsorRepository.save(newOne);
        } else {
            throw new NotFoundException();
        }
    }

    public Sponsor getByID(int id) {
        Optional<Sponsor> sponsor = sponsorRepository.findById(id);
        if (sponsor.isPresent()) {
            return sponsor.get();
        } else {
            throw new NotFoundException();
        }
    }


    public Sponsor getByInformation(Information information) {
        Optional<Sponsor> sponsor = Optional.of(sponsorRepository.findByInformation(information));
        if (sponsor.isPresent()) {
            return sponsor.get();
        } else {
            throw new NotFoundException();
        }
    }
}
