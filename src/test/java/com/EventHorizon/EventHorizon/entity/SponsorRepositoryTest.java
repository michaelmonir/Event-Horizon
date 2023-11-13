package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.SponsorRepository;
import com.EventHorizon.EventHorizon.services.OrganizerService;
import com.EventHorizon.EventHorizon.services.SponsorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SponsorRepositoryTest {
    @Autowired
    private SponsorService sponsorService;

    @Test
    public void save() {
        Information information = Information.builder().
                firstName("new2")
                .email("new2")
                .gender("new2")
                .lastName("new2")
                .role("new2")
                .password("new2")
                .payPalAccount("new@pay2")
                .userName("new2")
                .build();
        Sponsor sponsor = Sponsor.builder()
                .information(information)
                .build();
        sponsorService.add(sponsor);
    }

    @Test
    public void delete() {
        sponsorService.delete(1);
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
        Sponsor sponsor = Sponsor.builder()
                .information(information)
                .build();
        sponsorService.update(2, sponsor);
    }

    @Test
    public void getByID() {
        Sponsor sponsor = sponsorService.getByID(2);
        System.out.println("client = " + sponsor);
    }
}