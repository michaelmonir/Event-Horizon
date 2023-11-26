package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Exceptions.SponsorNotFoundException;
import com.EventHorizon.EventHorizon.services.InformationService;
import com.EventHorizon.EventHorizon.services.SponsorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SponsorRepositoryTest {
    @Autowired
    private SponsorService sponsorService;
    @Autowired
    private InformationService informationService;

    @Test
    public void add() {
        Information information = Information.builder().
                firstName("fares").email("30fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("sponsor").password("pass1234")
                .payPalAccount("30fares007@pay2").userName("30fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByID(sponsor.getId());
        Information i1 = informationService.getByID(s1.getInformation().getId());

        Assertions.assertEquals(information.getEmail(), i1.getEmail());
        Assertions.assertEquals(information.getFirstName(), i1.getFirstName());
        Assertions.assertEquals(information.getLastName(), i1.getLastName());
    }

    @Test
    public void delete() {
        Information information = Information.builder().
                firstName("fares").email("31fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("sponsor").password("pass1234")
                .payPalAccount("31fares007@pay2").userName("31fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        sponsorService.delete(sponsor.getId());

        Assertions.assertThrows(
                SponsorNotFoundException.class, () -> {
                    sponsorService.getByID(sponsor.getId());
                }
        );
    }

    @Test
    public void update() {
        Information information = Information.builder().
                firstName("fares").email("32fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("sponsor").password("pass1234")
                .payPalAccount("32fares007@pay2").userName("32fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);
        Information information2 = Information.builder().
                firstName("faris").email("33faris007@gmail.com")
                .gender("male").lastName("mohamed2")
                .role("sponsor").password("pass1234")
                .payPalAccount("33faris007@pay2").userName("33faris007")
                .build();
        Sponsor sponsor2 = Sponsor.builder().information(information2).build();
        sponsorService.update(sponsor.getId(), sponsor2);
        Sponsor s1 = sponsorService.getByID(sponsor.getId());
        Information i1 = informationService.getByID(s1.getInformation().getId());


        Assertions.assertEquals(information2.getEmail(), i1.getEmail());
        Assertions.assertEquals(information2.getFirstName(), i1.getFirstName());
        Assertions.assertEquals("mohamed2", i1.getLastName());
    }

    @Test
    public void getByID() {
        Information information = Information.builder().
                firstName("fares").email("34fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("sponsor").password("pass1234")
                .payPalAccount("34fares007@pay2").userName("34fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByID(sponsor.getId());


        Information i1 = informationService.getByID(s1.getInformation().getId());
        Assertions.assertEquals("34fares007@gmail.com", i1.getEmail());
        Assertions.assertEquals("fares", i1.getFirstName());
        Assertions.assertEquals("mohamed", i1.getLastName());
    }

    @Test
    public void getByInformation() {
        Information information = Information.builder().
                firstName("fares").email("35fares007@gmail.com")
                .gender("male").lastName("mohamed")
                .role("client").password("pass1234")
                .payPalAccount("35fares007@pay2").userName("35fares007")
                .build();
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByInformation(information);

        Assertions.assertEquals(s1, sponsor);
    }
}