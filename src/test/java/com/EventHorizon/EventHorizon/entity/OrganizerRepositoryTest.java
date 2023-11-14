package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.services.InformationService;
import com.EventHorizon.EventHorizon.services.ModeratorService;
import com.EventHorizon.EventHorizon.services.OrganizerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrganizerRepositoryTest {
    @Autowired
    private OrganizerService organizerService;

    @Autowired
    private InformationService informationService;

    @Test
    public void add() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("organizer").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);

        Organizer o1 = organizerService.getByID(organizer.getId());
        Information i1 = informationService.getByID(o1.getInformation().getId());

        Assertions.assertEquals(information.getEmail(), i1.getEmail());
        Assertions.assertEquals(information.getFirstName(), i1.getFirstName());
        Assertions.assertEquals(information.getLastName(), i1.getLastName());
    }


    @Test
    public void delete() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("organizer").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);

        organizerService.delete(organizer.getId());
        Organizer o1 = organizerService.getByID(organizer.getId());

        Assertions.assertEquals(o1, null);
    }

    @Test
    public void update() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("organizer").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Organizer organizer = Organizer.builder().information(information).build();
        organizerService.add(organizer);
        Information information2 = Information.builder().
                firstName("faris").email("faris007@gmail.com")
                .gender("male").lastName("mohamed2")
                .role("organizer").password("pass1234")
                .payPalAccount("faris007@pay2").userName("faris007")
                .build();
        Organizer organizer2 = Organizer.builder().information(information2).build();
        organizerService.update(organizer.getId(), organizer2);
        Organizer o1 = organizerService.getByID(organizer.getId());
        Information i1 = informationService.getByID(o1.getInformation().getId());


        Assertions.assertEquals(information2.getEmail(), i1.getEmail());
        Assertions.assertEquals(information2.getFirstName(), i1.getFirstName());
        Assertions.assertEquals("mohamed2", i1.getLastName());
    }

    @Test
    public void getByID() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("organizer").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Organizer client = Organizer.builder().information(information).build();
        organizerService.add(client);

        Organizer o1 = organizerService.getByID(client.getId());


        Information i1 = informationService.getByID(o1.getInformation().getId());
        Assertions.assertEquals("fares007@gmail.com", i1.getEmail());
        Assertions.assertEquals("fares", i1.getFirstName());
        Assertions.assertEquals("mohamed", i1.getLastName());
    }
}