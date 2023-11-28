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
    @Test
    public void updateWithDtoTest() {
        Information information = informationCreator.getInformation("ROLE_SPONSOR");
        Information information2 = informationCreator.getInformation("ROLE_SPONSOR");
        Sponsor sponsor = Sponsor.builder().information(information).build();
        sponsorService.add(sponsor);
        information2.setId(information.getId());
        UpdateInformationDTO updateInformationDTO = new UpdateInformationDTO(information2);
        ViewInformationDTO viewInformationDTO = informationService.updateWithDto(updateInformationDTO);
        Information i3 = informationService.getByEmail(information.getEmail());
        Assertions.assertEquals(i3.getFirstName(), information2.getFirstName());
        Assertions.assertEquals(i3.getGender(), information2.getGender());
        Assertions.assertEquals(i3.userName, information.userName);
        Assertions.assertEquals(i3.getId(), information.getId());
    }
}