package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.entity.Client;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.entity.Moderator;
import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.ModeratorRepository;
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
            Optional<Information> information1 =
                    Optional.ofNullable(informationService.getByEmail(moderator.getInformation().getEmail()));
            Optional<Information> information2 =
                    Optional.ofNullable(informationService.getByUserName(moderator.getInformation().getUserName()));
            if(information1.isPresent()){
                System.out.println("email is already Exist");
                return;
            }
            if(information2.isPresent()){
                System.out.println("username is already Exist");
                return;
            }
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
                System.out.println("NOT-FOUND");
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
                System.out.println("cant find");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Moderator getByID(int id) {
        try {
            Optional<Moderator> moderator = moderatorRepository.findById(id);
            return moderator.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
