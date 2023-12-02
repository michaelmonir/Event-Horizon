package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.Repository.*;
import com.EventHorizon.EventHorizon.Services.InformationServiceComponent.InformationServiceFactory;
import com.EventHorizon.EventHorizon.Services.InformationServiceComponent.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationService {
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    InformationServiceFactory informationServiceFactory;

    public void add(Information information) {
        UserInformationService myService =
                informationServiceFactory.getUserInformationServiceByRole(information.getRole());
        myService.add(information);
    }

    public void delete(int id) {
        Information information = this.getByID(id);
        UserInformationService myService =
                informationServiceFactory.getUserInformationServiceByRole(information.getRole());
        myService.delete(information);
    }


    public void update(int id, Information newOne) {
        Information oldOne = this.getByID(id);
        newOne.setId(oldOne.getId());
        informationRepository.save(newOne);
    }

    public ViewInformationDTO updateWithDto(UpdateInformationDTO updateInformationDTO) {
        Information information = this.getByID(updateInformationDTO.getId());
        UserInformationService myService = informationServiceFactory.getUserInformationServiceByRole(information.getRole());
        return new ViewInformationDTO(myService.update(updateInformationDTO, information));
    }


    public Information getByID(int id) {
        Optional<Information> information = informationRepository.findById(id);
        if (!information.isPresent())
            throw new InformationNotFoundException();

        return information.get();
    }

    public Information getByEmail(String email) {
        Optional<Information> information = Optional.ofNullable(informationRepository.findByEmail(email));
        if (!information.isPresent())
            throw new InformationNotFoundException();
        return information.get();
    }

    public Information getByUserName(String username) {
        Optional<Information> information = Optional.ofNullable(informationRepository.findByUserName(username));
        if (!information.isPresent())
            throw new InformationNotFoundException();

        return information.get();
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
