package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.InformationCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class InformationRepositoryTest {
    @Autowired
    private InformationRepositoryService informationService;
    @Autowired
    InformationCustomCreator informationCustomCreator;

    @Test
    ////// add information from factory and check if information added
    public void addInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        Information information2 = informationService.getByID(information.getId());
        Assertions.assertEquals(information2, information);
    }

    @Test
    public void deleteInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        informationService.delete(information.getId());
        Assertions.assertThrows(
                InformationNotFoundException.class, () -> {
                    informationService.getByID(information.getId());
                }
        );
    }

    @Test
    public void updateInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        Information information2 = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.update(information.getId(), information2);
        Information i1 = informationService.getByID(information.getId());
        Assertions.assertTrue(information2.equals(i1));
    }

    @Test
    public void getByIdInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        Information i1 = informationService.getByID(information.getId());
        Assertions.assertEquals(i1, information);
    }

    @Test
    public void getByEmailInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        Information i1 = informationService.getByEmail(information.getEmail());
        Assertions.assertEquals(i1, information);
    }

    @Test
    public void getByUsernameInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        Information i1 = informationService.getByUserName(information.userName);
        Assertions.assertEquals(i1, information);
    }


    @Test
    public void getByFirstnameInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        List<Information> i =
                informationService.getByFirstName(information.getFirstName());
        Assertions.assertEquals(i.get(0).getFirstName(), information.getFirstName());
    }


    @Test
    public void getByLastnameInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        List<Information> i =
                informationService.getByLastName(information.getLastName());
        Assertions.assertEquals(i.get(0).getLastName(), information.getLastName());
    }


    @Test
    public void getByGenderInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        List<Information> i =
                informationService.getByGender(information.getGender());
        Assertions.assertEquals(i.get(0).getGender(), information.getGender());
    }

    @Test
    public void getByRoleInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        List<Information> i =
                informationService.getByRole(information.getRole());
        Assertions.assertEquals(i.get(0).getRole(), information.getRole());
    }
    @Test
    public void getBySignInTestInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        List<Information> i =
                informationService.getBySignIn(0);
        Assertions.assertEquals(i.get(0).getSignInWithEmail(), information.getSignInWithEmail());
    }


    @Test
    public void updateWithDtoInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        Information information2 = informationCustomCreator.getInformation(Role.CLIENT);
        informationService.add(information);
        information2.setId(information.getId());

        UpdateInformationDTO updateInformationDTO = new UpdateInformationDTO(information2);

        ViewInformationDTO viewInformationDTO = informationService.updateWithDto(updateInformationDTO);

        Information i3 = informationService.getByEmail(information.getEmail());
        Assertions.assertEquals(i3.getFirstName(), information2.getFirstName());
        Assertions.assertEquals(i3.getGender(), information2.getGender());
        Assertions.assertEquals(i3.userName, information.userName);
        Assertions.assertEquals(i3.getId(), information.getId());
    }


    @Test
    public void getViewInformationDTOInformationTest() {
        Information information = informationCustomCreator.getInformation(Role.CLIENT);
        ViewInformationDTO informationDTO = new ViewInformationDTO(information);
        Assert.assertEquals(information.userName, informationDTO.getUserName());
        Assert.assertEquals(information.getFirstName(), informationDTO.getFirstName());
        Assert.assertEquals(information.getLastName(), informationDTO.getLastName());
        Assert.assertEquals(information.getEmail(), informationDTO.getEmail());
        Assert.assertEquals(information.getGender().toString(), informationDTO.getGender());
        Assert.assertEquals(information.getRole().toString(), informationDTO.getRole());
    }
}