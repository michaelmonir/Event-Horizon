package com.EventHorizon.EventHorizon.Repository.UserRepositoryTests;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdateDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.NotAdminOperationException;
import com.EventHorizon.EventHorizon.Repository.UserRepositories.InformationRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class AdminInformationServiceTest {

    @Autowired
    private InformationRepositoryService informationService;
    @Autowired
    private InformationRepository informationRepository;
    @Autowired
    private InformationCustomCreator informationCreator;

    @Test
    public void updateAdminDataTest() {
        Information information = informationCreator.getInformation(Role.ADMIN);
        information.setGender(Gender.NONE);
        informationRepository.save(information);
        information.setFirstName("newFirstName");
        information.setLastName("newLastName");
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(information);
        informationService.updateWithDto(userUpdateDTO);
        assertEquals(information, informationService.getByID(information.getId()));
    }

    @Test
    public void noAdminExceptionTest() {
        Information information = informationCreator.getInformation(Role.ADMIN);
        information.setGender(Gender.NONE);

        informationRepository.save(information);
        informationRepository.deleteById(information.getId());
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(information);
        assertThrows(
                InformationNotFoundException.class, () -> {
                    informationService.updateWithDto(userUpdateDTO);
                }
        );
    }

    @Test
    public void NotAdminOperationExceptionTest() {
        Information information = informationCreator.getInformation(Role.ADMIN);
        information.setGender(Gender.NONE);
        assertThrows(
                NotAdminOperationException.class, () -> {
                    informationService.add(information);
                }
        );
    }

}
