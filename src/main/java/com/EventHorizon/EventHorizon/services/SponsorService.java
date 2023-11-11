package com.EventHorizon.EventHorizon.services;

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
        try {
            Optional<Information> information1 =
                    Optional.ofNullable(informationService.getByEmail(sponsor.getInformation().getEmail()));
            Optional<Information> information2 =
                    Optional.ofNullable(informationService.getByUserName(sponsor.getInformation().getUserName()));
            if(information1.isPresent()){
                System.out.println("email is already Exist");
                return;
            }
            if(information2.isPresent()){
                System.out.println("username is already Exist");
                return;
            }
            sponsorRepository.save(sponsor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Optional<Sponsor> sponsor = sponsorRepository.findById(id);
            if (sponsor.isPresent()) {
                sponsorRepository.deleteById(id);
            } else {
                System.out.println("NOT-FOUND");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(int id, Sponsor newOne) {
        try {
            Optional<Sponsor> old = sponsorRepository.findById(id);
            if (old.isPresent()) {
                Sponsor oldOne = old.get();
                informationService.update(oldOne.getInformation().getId(), newOne.getInformation());
                newOne.setId(oldOne.getId());
                sponsorRepository.save(newOne);
            } else {
                System.out.println("cant find");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Sponsor getByID(int id) {
        try {
            Optional<Sponsor> sponsor = sponsorRepository.findById(id);
            return sponsor.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
