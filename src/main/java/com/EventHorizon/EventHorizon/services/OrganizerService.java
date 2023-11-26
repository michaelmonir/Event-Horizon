package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.Exceptions.AlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.entity.Organizer;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
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
        organizerRepository.save(organizer);
    }

    public void delete(int id) {
            Optional<Organizer> organizer = organizerRepository.findById(id);
            if (organizer.isPresent()) {
                organizerRepository.deleteById(id);
            } else {
                throw new NotFoundException();
            }
    }


    public void update(int id, Organizer newOne) {
        Optional<Organizer> old = organizerRepository.findById(id);
        if (old.isPresent()) {
            Organizer oldOne = old.get();
            informationService.update(oldOne.getInformation().getId(), newOne.getInformation());
            newOne.setId(oldOne.getId());
            organizerRepository.save(newOne);
        } else {
            throw new NotFoundException();
        }
    }

    public Organizer getByID(int id) {
        Optional<Organizer> organizer = organizerRepository.findById(id);
        if (organizer.isPresent()) {
            return organizer.get();
        } else {
            throw new NotFoundException();
        }
    }

    public Organizer getByInformation(Information information) {
        Optional<Organizer> organizer = Optional.of(organizerRepository.findByInformation(information));
        if (organizer.isPresent()) {
            return organizer.get();
        } else {
            throw new NotFoundException();
        }
    }
}
