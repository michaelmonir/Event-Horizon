package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
import com.EventHorizon.EventHorizon.services.ModeratorService;
import com.EventHorizon.EventHorizon.services.OrganizerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrganizerRepositoryTest {

    @Autowired
    private OrganizerService organizerService;

    @Test
    public void save() {
        Information information = Information.builder().
                firstName("nfarisew2a")
                .email("new2a")
                .gender("newa2")
                .lastName("newa2")
                .role("new2a")
                .password("neaw2")
                .payPalAccount("newdsd@pay2")
                .userName("newasdas2")
                .build();
        Organizer organizer = Organizer.builder()
                .information(information)
                .build();
        organizerService.add(organizer);
    }

    @Test
    public void delete() {
        organizerService.delete(1);
    }


    @Test
    public void update() {
        Information information = Information.builder().
                firstName("done")
                .email("done")
                .gender("done")
                .lastName("done")
                .role("done")
                .password("done")
                .payPalAccount("@done")
                .userName("done")
                .build();
        Organizer organizer = Organizer.builder()
                .information(information)
                .build();
        organizerService.update(2, organizer);
    }

    @Test
    public void getByID() {
        Organizer organizer = organizerService.getByID(2);
        System.out.println("client = " + organizer);
    }
}