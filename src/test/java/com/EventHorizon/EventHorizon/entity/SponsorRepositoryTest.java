package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.repository.ClientRepository;
import com.EventHorizon.EventHorizon.repository.SponsorRepository;
import com.EventHorizon.EventHorizon.services.InformationService;
import com.EventHorizon.EventHorizon.services.OrganizerService;
import com.EventHorizon.EventHorizon.services.SponsorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SponsorRepositoryTest {
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private InformationService informationService;

    @Test
    public void add() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("sponsor").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByID(sponsor.getId());
        Information i1 = informationService.getByID(s1.getInformation().getId());

        Assertions.assertEquals(information.getEmail(), i1.getEmail());
        Assertions.assertEquals(information.getFirstName(), i1.getFirstName());
        Assertions.assertEquals(information.getLastName(), i1.getLastName());
        sponsorService.delete(sponsor.getId());
    }

    @Test
    public void delete() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("sponsor").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        sponsorService.delete(sponsor.getId());
        Sponsor s1 = sponsorService.getByID(sponsor.getId());

        Assertions.assertEquals(s1, null);
    }

    @Test
    public void update() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("sponsor").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);
        Information information2 = Information.builder().
                firstName("faris").email("faris007@gmail.com")
                .gender("male").lastName("mohamed2")
                .role("sponsor").password("pass1234")
                .payPalAccount("faris007@pay2").userName("faris007")
                .build();
        Sponsor sponsor2 = Sponsor.builder().information(information2).build();
        sponsorService.update(sponsor.getId(), sponsor2);
        Sponsor s1 = sponsorService.getByID(sponsor.getId());
        Information i1 = informationService.getByID(s1.getInformation().getId());


        Assertions.assertEquals(information2.getEmail(), i1.getEmail());
        Assertions.assertEquals(information2.getFirstName(), i1.getFirstName());
        Assertions.assertEquals("mohamed2", i1.getLastName());
        sponsorService.delete(sponsor.getId());
    }

    @Test
    public void getByID() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("sponsor").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByID(sponsor.getId());


        Information i1 = informationService.getByID(s1.getInformation().getId());
        Assertions.assertEquals("fares007@gmail.com", i1.getEmail());
        Assertions.assertEquals("fares", i1.getFirstName());
        Assertions.assertEquals("mohamed", i1.getLastName());
        sponsorService.delete(sponsor.getId());
    }

    @Test
    public void getByInformation() {
        Information information = Information.builder().
                firstName("fares").email("fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("fares007@pay2").userName("fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByInformation(information);

        Assertions.assertEquals(s1, sponsor);
        sponsorService.delete(sponsor.getId());
    }

}