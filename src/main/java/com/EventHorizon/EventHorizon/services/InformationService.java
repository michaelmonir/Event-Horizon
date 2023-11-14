package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationService {
    @Autowired
    InformationRepository informationRepository;
    public void add(Information information) {
        //// here we implement logic of create user  for ahmed hassan proxy

        try {

            informationRepository.save(information);


        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public void delete(int id) {
        try {
            Optional<Information> information = informationRepository.findById(id);
            if (information.isPresent()) {
                informationRepository.deleteById(id);
            } else {

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, Information newOne) {
        try {
            Optional<Information> old = informationRepository.findById(id);
            if (old.isPresent()) {
                Information oldOne = old.get();
                newOne.setId(oldOne.getId());
                informationRepository.save(newOne);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Information getByID(int id) {
        try {
            Optional<Information> information = informationRepository.findById(id);
            if (information.isPresent()) {
                return information.orElse(null);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Information getByEmail(String email) {
        try {
            Optional<Information> information = Optional.ofNullable(informationRepository.findByEmail(email));
            if (information.isPresent()) {
                return information.orElse(null);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Information getByUserName(String username) {
        try {
            Optional<Information> information = Optional.ofNullable(informationRepository.findByUserName(username));
            if (information.isPresent()) {
                return information.orElse(null);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Information> getByLastName(String lastname) {
        try {
            List<Information> list = informationRepository.findByLastName(lastname);
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Information> getByFirstName(String firstname) {
        try {
            List<Information> list = informationRepository.findByFirstName(firstname);
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Information> getByGender(String gender) {
        try {
            List<Information> list = informationRepository.findByGender(gender);
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Information> getByRole(String role) {
        try {
            List<Information> list = informationRepository.findByRole(role);
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
