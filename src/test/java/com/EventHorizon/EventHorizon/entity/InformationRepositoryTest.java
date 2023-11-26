package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Exceptions.InformationNotFoundException;
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


    @Test
    public void add() {
        Information information = Information.builder().
                firstName("fares").email("7fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("7fares007@pay2").userName("7fares007")
                .build();
        informationService.add(information);
        Client c1 = clientService.getByInformation(information);
        Assertions.assertEquals(c1.getInformation(), information);
    }

    @Test
    public void delete() {
        Information information = Information.builder().
                firstName("fares").email("8fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("8fares007@pay2").userName("8fares007")
                .build();
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
        Information information = Information.builder().
                firstName("fares").email("9fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("9fares007@pay2").userName("9fares007")
                .build();
        informationService.add(information);
        Information information2 = Information.builder().
                firstName("moo").email("10fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("10fares007@pay2").userName("10fares007")
                .build();
        informationService.update(information.getId(), information2);
        Information i1 = informationService.getByID(information.getId());
        Assertions.assertEquals(i1.getFirstName(), "moo");
    }

    @Test
    public void getByID() {
        Information information = Information.builder().
                firstName("fares").email("11fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("11fares007@pay2").userName("11fares007")
                .build();
        informationService.add(information);
        Information i1 = informationService.getByID(information.getId());
        Assertions.assertEquals(i1, information);
    }
    @Test
    public void getByEmail() {
        Information information = Information.builder().
                firstName("fares").email("12fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("12fares007@pay2").userName("12fares007")
                .build();
        informationService.add(information);
        Information i1 = informationService.getByEmail(information.getEmail());
        Assertions.assertEquals(i1, information);
    }

    @Test
    public void getByUsername() {
        Information information = Information.builder().
                firstName("fares").email("13fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("13fares007@pay2").userName("13fares007")
                .build();
        informationService.add(information);
        Information i1 = informationService.getByUserName(information.userName);
        Assertions.assertEquals(i1, information);
    }


    @Test
    public void getByFirstname() {
        Information information = Information.builder().
                firstName("fares").email("14fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("14fares007@pay2").userName("14fares007")
                .build();
        informationService.add(information);
        List<Information> i =
                informationService.getByFirstName(information.getFirstName());
        Assertions.assertEquals(i.get(0).getFirstName(), information.getFirstName());
    }


    @Test
    public void getByLastname() {
        Information information = Information.builder().
                firstName("fares").email("15fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("15fares007@pay2").userName("15fares007")
                .build();
        informationService.add(information);
        List<Information> i =
                informationService.getByLastName(information.getLastName());
        Assertions.assertEquals(i.get(0).getLastName(), information.getLastName());
    }


    @Test
    public void getByGender() {
        Information information = Information.builder().
                firstName("fares").email("16fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("16fares007@pay2").userName("16fares007")
                .build();
        informationService.add(information);
        List<Information> i =
                informationService.getByGender(information.getGender());
        Assertions.assertEquals(i.get(0).getGender(), information.getGender());
    }

    @Test
    public void getByRole() {
        Information information = Information.builder().
                firstName("fares").email("17fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("ROLE_CLIENT").password("pass1234")
                .payPalAccount("17fares007@pay2").userName("17fares007")
                .build();
        informationService.add(information);
        List<Information> i =
                informationService.getByRole(information.getRole());
        Assertions.assertEquals(i.get(0).getRole(), information.getRole());
    }
}