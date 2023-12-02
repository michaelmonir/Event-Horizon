package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ModeratorNotFoundException;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Repository.ModeratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeratorService {
    @Autowired
    ModeratorRepository moderatorRepository;


    public void add(Moderator moderator) {
            moderatorRepository.save(moderator);
    }

    public void delete(int id) {
        Optional<Moderator> moderator = moderatorRepository.findById(id);
        if (moderator.isPresent()) {
            moderatorRepository.deleteById(id);
        } else {
            throw new ModeratorNotFoundException();
        }
    }

    public Moderator getByInformation(Information information) {
        Optional<Moderator> moderator = Optional.ofNullable(moderatorRepository.findByInformation(information));
        if (moderator.isPresent()) {
            return moderator.get();
        } else {
            throw new ModeratorNotFoundException();
        }
    }
}
