package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.NotAdminOperationException;
import com.EventHorizon.EventHorizon.Repository.InformationRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.InformationRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.SuperUserInformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.UserInformationRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationRepositoryService {

    @Autowired
    InformationRepository informationRepository;
    @Autowired
    InformationRepositoryServiceFactory informationServiceFactory;

    public void add(Information information) {
        if (information.getRole() == Role.ADMIN) {
            throw new NotAdminOperationException();
        }
        UserInformationRepositoryService myService =
                (UserInformationRepositoryService) informationServiceFactory.getUserInformationServiceByRole(information.getRole());
        myService.add(information);
    }

    public void delete(int id) {
        Information information = this.getByID(id);
        if (information.getRole() == Role.ADMIN) {
            throw new NotAdminOperationException();
        }
        UserInformationRepositoryService myService =
                (UserInformationRepositoryService) informationServiceFactory.getUserInformationServiceByRole(information.getRole());
        myService.delete(information);
    }

    public void update(int id, Information newOne) {
        Information oldOne = this.getByID(id);
        newOne.setId(oldOne.getId());
        informationRepository.save(newOne);
    }

    public ViewInformationDTO updateWithDto(UpdateInformationDTO updateInformationDTO) {
        Information information = this.getByID(updateInformationDTO.getId());
        SuperUserInformationRepositoryService myService = informationServiceFactory.getUserInformationServiceByRole(information.getRole());
        return new ViewInformationDTO(myService.update(updateInformationDTO, information));
    }

    public User getUserByInformation(Information information) {

        UserInformationRepositoryService myService =
                (UserInformationRepositoryService) informationServiceFactory.
                        getUserInformationServiceByRole(information.getRole());
        return myService.getUserByInformation(information);
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

    public boolean existsByEmail(String email) {
        Optional<Information> information = Optional.ofNullable(informationRepository.findByEmail(email));
        return information.isPresent();
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

    public List<Information> getByGender(Gender gender) {
        List<Information> list = informationRepository.findByGender(gender);
        return list;
    }

    public List<Information> getByRole(Role role) {
        List<Information> list = informationRepository.findByRole(role);
        return list;
    }

    public List<Information> getBySignIn(int value) {
        List<Information> list = informationRepository.findBySignInWithEmail(value);
        return list;
    }

    public UserInformationRepositoryService getService(Role role) {
        UserInformationRepositoryService myService =
                (UserInformationRepositoryService) informationServiceFactory.getUserInformationServiceByRole(role);
        return myService;
    }

}
