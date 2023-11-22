package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
import com.EventHorizon.EventHorizon.services.InformationServiceModified.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeratorService {
    @Autowired
    ModeratorRepository moderatorRepository;
    @Autowired
    InformationService informationService;

    public void add(Moderator moderator) {
        try {
            moderatorRepository.save(moderator);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            Optional<Moderator> moderator = moderatorRepository.findById(id);
            if (moderator.isPresent()) {
                moderatorRepository.deleteById(id);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void update(int id, Moderator newOne) {
        try {
            Optional<Moderator> old = moderatorRepository.findById(id);
            if (old.isPresent()) {
                Moderator oldOne = old.get();
                informationService.update(oldOne.getInformation().getId(), newOne.getInformation());
                newOne.setId(oldOne.getId());
                moderatorRepository.save(newOne);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Moderator getByID(int id) {
        try {
            Optional<Moderator> moderator = moderatorRepository.findById(id);
            if (moderator.isPresent()) {
                return moderator.orElse(null);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Moderator getByInformation(Information information) {
        try {
            Optional<Moderator> moderator = Optional.of(moderatorRepository.findByInformation(information));
            if (moderator.isPresent()) {
                return moderator.orElse(null);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
