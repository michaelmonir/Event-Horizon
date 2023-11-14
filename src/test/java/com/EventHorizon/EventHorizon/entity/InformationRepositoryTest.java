package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.InformationRepository;
import com.EventHorizon.EventHorizon.services.*;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, true);
        Client c1 = clientService.getByInformation(information);
        Assertions.assertEquals(c1.getInformation(), information);
        clientService.delete(c1.getId());
    }

    @Test
    public void delete() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        informationService.delete(information.getId());
        Information i1 = informationService.getByID(information.getId());
        Assertions.assertEquals(i1, null);

    }

    @Test
    public void update() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        Information information2 = Information.builder().
                firstName("moo").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.update(information.getId(), information2);
        Information i1 = informationService.getByID(information.getId());
        Assertions.assertEquals(i1.getFirstName(), "moo");
        informationService.delete(information2.getId());
    }

    @Test
    public void getByID() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        Information i1 = informationService.getByID(information.getId());
        Assertions.assertEquals(i1, information);
        informationService.delete(information.getId());
    }


    @Test
    public void getByEmail() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        Information i1 = informationService.getByEmail(information.getEmail());
        Assertions.assertEquals(i1, information);
        informationService.delete(information.getId());
    }

    @Test
    public void getByUsername() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        Information i1 = informationService.getByUserName(information.getUserName());
        Assertions.assertEquals(i1, information);
        informationService.delete(information.getId());
    }


    @Test
    public void getByFirstname() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        List<Information> i =
                informationService.getByFirstName(information.getFirstName());
        Assertions.assertEquals(i.get(0), information);
        informationService.delete(information.getId());
    }


    @Test
    public void getByLastname() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        List<Information> i =
                informationService.getByLastName(information.getLastName());
        Assertions.assertEquals(i.get(0), information);
        informationService.delete(information.getId());
    }


    @Test
    public void getByGender() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        List<Information> i =
                informationService.getByGender(information.getGender());
        Assertions.assertEquals(i.get(0), information);
        informationService.delete(information.getId());
    }

    @Test
    public void getByRole() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("Client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        informationService.add(information, false);
        List<Information> i =
                informationService.getByRole(information.getRole());
        Assertions.assertEquals(i.get(0), information);
        informationService.delete(information.getId());
    }
}