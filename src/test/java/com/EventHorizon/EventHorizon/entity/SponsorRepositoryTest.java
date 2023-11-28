package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Organizer;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.SponsorNotFoundException;
import com.EventHorizon.EventHorizon.Services.InformationService;
import com.EventHorizon.EventHorizon.Services.SponsorService;
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
        informationService.add(information);

        Sponsor s1 = sponsorService.getByInformation(information);
        Information i1 = informationService.getByID(s1.getInformation().getId());

        Assertions.assertTrue(information.equals(i1));

    }

    @Test
    public void delete() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        informationService.add(information);
        informationService.delete(information.getId());

        Assertions.assertThrows(
                SponsorNotFoundException.class, () -> {
                    sponsorService.getByID(information.getId());
                }
        );
    }


    @Test
    public void getByID() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        informationService.add(information);

        Sponsor s = sponsorService.getByInformation(information);
        Sponsor s1 = sponsorService.getByID(s.getId());

        Information i1 = informationService.getByID(s1.getInformation().getId());

        Assertions.assertTrue(information.equals(i1));

    }

    @Test
    public void getByInformation() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        informationService.add(information);

        Sponsor s1 = sponsorService.getByInformation(information);

        Assertions.assertEquals(s1.getInformation(), information);
    }
}