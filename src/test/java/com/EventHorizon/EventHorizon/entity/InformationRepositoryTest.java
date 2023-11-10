package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.InformationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest

class InformationRepositoryTest {
    @Autowired
    private InformationRepository informationRepository;
    @Test
    public void save() {
        Information information = Information.builder().
                firstName("faris")
                .email("faris007@gmail.com")
                .gender("male")
                .lastName("moahmed")
                .role("organizer")
                .password("pass1234")
                .payPalAccount("faris0007@pay")
                .userName("faris00")
                .active(0)
                .build();
        informationRepository.save(information);
    }




}