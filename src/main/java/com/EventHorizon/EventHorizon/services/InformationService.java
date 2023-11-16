package com.EventHorizon.EventHorizon.services;

import com.EventHorizon.EventHorizon.Exceptions.NotFoundException;
import com.EventHorizon.EventHorizon.entity.*;
import com.EventHorizon.EventHorizon.repository.*;
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

    public void add(Information information,boolean addUser) {
        //// here we implement logic of create user  for ahmed hassan proxy
        try {
            if(!addUser) {
                informationRepository.save(information);
            }
            else if (information.getRole().equals("ROLE_CLIENT")) {
                Client c1 = Client.builder().information(information).build();
                clientRepository.save(c1);
            } else if (information.getRole().equals("ROLE_MODERATOR")) {
                Moderator m1 = Moderator.builder().information(information).build();
                moderatorRepository.save(m1);
            } else if (information.getRole().equals("ROLE_ORGANIZER")) {
                Organizer o1 = Organizer.builder().information(information).build();
                organizerRepository.save(o1);
            } else {
                Sponsor s1 = Sponsor.builder().information(information).build();
                sponsorRepository.save(s1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {

            Optional<Information> informationOp = informationRepository.findById(id);
            if (informationOp.isPresent()) {
                Information information=informationOp.get();
                if (information.getRole().equals("ROLE_CLIENT")) {
                    Client c1 = clientRepository.findByInformation(information);
                    clientRepository.delete(c1);
                } else if (information.getRole().equals("ROLE_MODERATOR")) {
                    Moderator m1 = moderatorRepository.findByInformation(information);
                    moderatorRepository.delete(m1);
                } else if (information.getRole().equals("ROLE_ORGANIZER")) {
                    Organizer o1 = organizerRepository.findByInformation(information);
                    organizerRepository.delete(o1);
                } else {
                    Sponsor s1 = sponsorRepository.findByInformation(information);
                    sponsorRepository.delete(s1);
                }
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

    public List<Information> getBySignIn(int value) {
        try {
            List<Information> list = informationRepository.findBySignInWithEmail(value);
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
