package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.SponsorNotFoundException;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.SponsorInformationRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SponsorRepositoryTest {
    @Autowired
    private SponsorInformationRepositoryService sponsorInformationService;
    @Autowired
    InformationCreator informationCreator;

    @Test
    public void addSponsorTest() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        sponsorInformationService.add(information);

        Sponsor s1 = (Sponsor) sponsorInformationService.getUserByInformation(information);

        Assertions.assertTrue(information.equals(s1.getInformation()));

    }

    @Test
    public void deleteSponsorTest() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        sponsorInformationService.add(information);
        sponsorInformationService.delete(information);

        Assertions.assertThrows(
                SponsorNotFoundException.class, () -> {
                    sponsorInformationService.getUserByInformation(information);
                }
        );
    }

    @Test
    public void getByInformationSponsorTest() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        sponsorInformationService.add(information);

        Sponsor s1 = (Sponsor) sponsorInformationService.getUserByInformation(information);

        Assertions.assertEquals(s1.getInformation(), information);
    }
}