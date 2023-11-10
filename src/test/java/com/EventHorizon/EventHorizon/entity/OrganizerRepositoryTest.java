package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.OrganizerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrganizerRepositoryTest {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Test
    public void save() {
        Information information = Information.builder().
                firstName("said")
                .email("mamo@gmail.com")
                .gender("male")
                .lastName("ahmed")
                .role("Organizer")
                .password("pass4321")
                .payPalAccount("ahmed007@pay")
                .userName("ahmed_hossam")
                .build();
        Organizer organizer = Organizer.builder()
                .information(information)
                .rate(5.5)
                .build();
        organizerRepository.save(organizer);
    }
}