package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.services.InformationServiceModified.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerService {


    @Autowired
    OrganizerRepository organizerRepository;
    @Autowired
    InformationService informationService;

    public void add(Organizer organizer) {
        try {
            organizerRepository.save(organizer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Optional<Organizer> organizer = organizerRepository.findById(id);
            if (organizer.isPresent()) {
                organizerRepository.deleteById(id);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(int id, Organizer newOne) {
        try {
            Optional<Organizer> old = organizerRepository.findById(id);
            if (old.isPresent()) {
                Organizer oldOne = old.get();
                informationService.update(oldOne.getInformation().getId(), newOne.getInformation());
                newOne.setId(oldOne.getId());
                organizerRepository.save(newOne);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Organizer getByID(int id) {
        try {
            Optional<Organizer> organizer = organizerRepository.findById(id);
            if (organizer.isPresent()) {
                return organizer.orElse(null);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Organizer getByInformation(Information information) {
        try {
            Optional<Organizer> organizer = Optional.of(organizerRepository.findByInformation(information));
            if (organizer.isPresent()) {
                return organizer.orElse(null);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
