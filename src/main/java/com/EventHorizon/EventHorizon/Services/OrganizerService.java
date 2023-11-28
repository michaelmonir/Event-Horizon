package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.OrganizerNotFoundException;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizerService {


    @Autowired
    OrganizerRepository organizerRepository;

    public void add(Organizer organizer) {
        organizerRepository.save(organizer);
    }

    public void delete(int id) {
            Optional<Organizer> organizer = organizerRepository.findById(id);
            if (organizer.isPresent()) {
                organizerRepository.deleteById(id);
            } else {
                throw new OrganizerNotFoundException();
            }
    }


    public Organizer getByID(int id) {
        Optional<Organizer> organizer = organizerRepository.findById(id);
        if (organizer.isPresent()) {
            return organizer.get();
        } else {
            throw new OrganizerNotFoundException();
        }
    }

    public Organizer getByInformation(Information information) {
        Optional<Organizer> organizer = Optional.of(organizerRepository.findByInformation(information));
        if (organizer.isPresent()) {
            return organizer.get();
        } else {
            throw new OrganizerNotFoundException();
        }
    }
}
