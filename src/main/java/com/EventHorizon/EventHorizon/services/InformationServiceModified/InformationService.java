package com.EventHorizon.EventHorizon.services.InformationServiceModified;

import com.EventHorizon.EventHorizon.Exceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.*;
import com.EventHorizon.EventHorizon.repository.*;
import com.EventHorizon.EventHorizon.services.InformationServiceMichael.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationService {
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    ClientRepository clientRepository ;
    @Autowired
    ModeratorRepository moderatorRepository;
    @Autowired
    OrganizerRepository organizerRepository;
    @Autowired
    SponsorRepository sponsorRepository;
    @Autowired
    InformationServiceFactory informationServiceFactory;

    private Information getInformation(int id)
    {
        Optional<Information> informationOp = informationRepository.findById(id);
        if (!informationOp.isPresent())
            throw new InformationNotFoundException();
        return informationOp.get();
    }

    public void add(Information information,boolean addUser) {
        //// here we implement logic of create user  for ahmed hassan proxy
        if(!addUser) {
            informationRepository.save(information);
        }
        else {
            UserInformationService userInformationService =
                    this.informationServiceFactory.getUserInformationServiceByRole(information.getRole());
        }
    }

    public void delete(int id) {
        Information information = this.getInformation(id);
        UserInformationService userInformationService =
                this.informationServiceFactory.getUserInformationServiceByRole(information.getRole());
        userInformationService.delete(id, information);
    }

    public void update(int id, Information newOne) {
        Optional<Information> old = informationRepository.findById(id);
        if (old.isPresent()) {
            Information oldOne = old.get();
            newOne.setId(oldOne.getId());
            informationRepository.save(newOne);
        } else {
            throw new NotFoundException();
        }
    }

    public Information getByID(int id) {
        Optional<Information> information = informationRepository.findById(id);
        if (information.isPresent()) {
            return information.orElse(null);
        } else {
            throw new NotFoundException();
        }
    }

    //////////// return null here should be updated
    public Information getByEmail(String email) {
        Optional<Information> information = Optional.ofNullable(informationRepository.findByEmail(email));
        if (information.isPresent()) {
            return information.orElse(null);
        } else {
            throw new NotFoundException();
        }
    }

    //////////// return null here should be updated
    public Information getByUserName(String username) {
        Optional<Information> information = Optional.ofNullable(informationRepository.findByUserName(username));
        if (information.isPresent()) {
            return information.orElse(null);
        } else {
            throw new NotFoundException();
        }
    }

    public List<Information> getByLastName(String lastname) {
        List<Information> list = informationRepository.findByLastName(lastname);
        return list;
    }

    public List<Information> getByFirstName(String firstname) {
        List<Information> list = informationRepository.findByFirstName(firstname);
        return list;
    }

    public List<Information> getByGender(String gender) {
        List<Information> list = informationRepository.findByGender(gender);
        return list;
    }

    public List<Information> getByRole(String role) {
        List<Information> list = informationRepository.findByRole(role);
        return list;
    }

    public List<Information> getBySignIn(int value) {
        List<Information> list = informationRepository.findBySignInWithEmail(value);
        return list;
    }
}
