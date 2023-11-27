package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class InformationRepositoryTest {
    @Autowired
    private InformationService informationService;
    @Autowired
    ClientService clientService;
    @Autowired
    InformationCreator informationCreator;

    @Test
    public void add() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        Client c1 = clientService.getByInformation(information);
        Assertions.assertEquals(c1.getInformation(), information);
    }

    @Test
    public void delete() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        informationService.delete(information.getId());
        Assertions.assertThrows(
                InformationNotFoundException.class, () -> {
                    informationService.getByID(information.getId());
                }
        );
    }

    @Test
    public void update() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        Information information2 = informationCreator.getInformation("ROLE_CLIENT");
        informationService.update(information.getId(), information2);
        Information i1 = informationService.getByID(information.getId());

        Assertions.assertTrue(information2.equals(i1));
    }

    @Test
    public void getByID() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        Information i1 = informationService.getByID(information.getId());
        Assertions.assertEquals(i1, information);
    }
    @Test
    public void getByEmail() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        Information i1 = informationService.getByEmail(information.getEmail());
        Assertions.assertEquals(i1, information);
    }

    @Test
    public void getByUsername() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        Information i1 = informationService.getByUserName(information.userName);
        Assertions.assertEquals(i1, information);
    }


    @Test
    public void getByFirstname() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        List<Information> i =
                informationService.getByFirstName(information.getFirstName());
        Assertions.assertEquals(i.get(0).getFirstName(), information.getFirstName());
    }


    @Test
    public void getByLastname() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        List<Information> i =
                informationService.getByLastName(information.getLastName());
        Assertions.assertEquals(i.get(0).getLastName(), information.getLastName());
    }


    @Test
    public void getByGender() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        List<Information> i =
                informationService.getByGender(information.getGender());
        Assertions.assertEquals(i.get(0).getGender(), information.getGender());
    }

    @Test
    public void getByRole() {
        Information information = informationCreator.getInformation("ROLE_CLIENT");
        informationService.add(information);
        List<Information> i =
                informationService.getByRole(information.getRole());
        Assertions.assertEquals(i.get(0).getRole(), information.getRole());
    }
}