package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.SponsorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SponsorRepositoryTest {
    @Autowired
    private SponsorRepository sponsorRepository;

    @Test
    public void save() {
        Information information = Information.builder().
                firstName("ahmed")
                .email("ahmed@gmail.com")
                .gender("male")
                .lastName("ahmed")
                .role("sponsor")
                .password("pass4321")
                .payPalAccount("ahmed007@pay")
                .userName("ahmed_hossam")
                .build();
        Sponsor sponsor = Sponsor.builder()
                .information(information)
                .build();
        sponsorRepository.save(sponsor);
    }
}