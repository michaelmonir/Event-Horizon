package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.InformationRepository;
import com.EventHorizon.EventHorizon.services.InformationService;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest

class InformationRepositoryTest {
    @Autowired
    private InformationService informationService;

    @Test
    public void add() {
        Information information = Information.builder().
                firstName("mamfadasdsado")
                .email("faris0asdsad07@dasdasdsad.com")
                .gender("lronaasdasdldo@asdsad.com")
                .lastName("moaadssadhmed")
                .role("organizsasadasddsadasder")
                .password("passadad1234")
                .payPalAccount("fasdsadaaris0dasdsad007@pay")
                .userName("faris0sadassdsaddsad0")
                .build();
        informationService.add(information);
    }

    @Test
    public void delete() {
        informationService.delete(8);
    }

    @Test
    public void update() {
        Information information = Information.builder().
                firstName("admina")
                .email("admina")
                .gender("admina")
                .lastName("admina")
                .role("admina")
                .password("admina")
                .payPalAccount("@admina")
                .userName("admina")
                .build();
        informationService.update(1, information);
    }

    @Test
    public void getByID() {
        Information i = informationService.getByID(1);
        System.out.println("i = " + i);
    }


    @Test
    public void getByEmail() {
        Information i = informationService.getByEmail("admina");
        System.out.println("i = " + i);
    }

    @Test
    public void getByUsername() {
        Information i =
                informationService.getByUserName("hamo");
        System.out.println("i = " + i);
    }


    @Test
    public void getByFirstname() {
        List<Information> i =
                informationService.getByFirstName("leo");
        System.out.println("i = " + i);
    }


    @Test
    public void getByLastname() {
        List<Information> i =
                informationService.getByLastName("hamo");
        System.out.println("i = " + i);
    }


    @Test
    public void getByGender() {
        List<Information> i =
                informationService.getByGender("male");
        System.out.println("i = " + i);
    }

    @Test
    public void getByRole() {
        List<Information> i =
                informationService.getByRole("client");
        System.out.println("i = " + i);
    }
}