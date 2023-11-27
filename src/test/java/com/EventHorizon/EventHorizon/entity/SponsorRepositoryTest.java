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
    @Autowired
    InformationCreator informationCreator;
    @Test
    public void add() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByID(sponsor.getId());
        Information i1 = informationService.getByID(s1.getInformation().getId());

        Assertions.assertTrue(information.equals(i1));

    }

    @Test
    public void delete() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
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
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);
        Information information2 = informationCreator.getInformation("ROLE_SPONSOR");
        Sponsor sponsor2 = Sponsor.builder().information(information2).build();
        sponsorService.update(sponsor.getId(), sponsor2);
        Sponsor s1 = sponsorService.getByID(sponsor.getId());
        Information i1 = informationService.getByID(s1.getInformation().getId());

        Assertions.assertTrue(information2.equals(i1));
    }

    @Test
    public void getByID() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByID(sponsor.getId());


        Information i1 = informationService.getByID(s1.getInformation().getId());

        Assertions.assertTrue(information.equals(i1));

    }

    @Test
    public void getByInformation() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);

        Sponsor s1 = sponsorService.getByInformation(information);

        Assertions.assertEquals(s1, sponsor);
    }
}