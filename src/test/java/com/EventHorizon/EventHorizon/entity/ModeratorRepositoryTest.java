package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ModeratorNotFoundException;
import com.EventHorizon.EventHorizon.Services.InformationService;
import com.EventHorizon.EventHorizon.Services.ModeratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModeratorRepositoryTest {
    @Autowired
    private ModeratorService moderatorService;
    @Autowired
    private InformationService informationService;
    @Autowired
    InformationCreator informationCreator;
    @Test
    public void add() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        informationService.add(information);

        /// we can add by two ways from servics or from moderator
//        Moderator moderator = Moderator.builder().information(information).build();
//        moderatorService.add(moderator);

        Moderator m1 = moderatorService.getByInformation(information);
        Information i1 = informationService.getByID(m1.getInformation().getId());


        Assertions.assertTrue(information.equals(i1));
    }

    @Test
    public void delete() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);

        moderatorService.delete(moderator.getId());
        Assertions.assertThrows(
               ModeratorNotFoundException.class, () -> {
                    moderatorService.getByID(moderator.getId());
                }
        );
    }



    @Test
    public void getByID() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);
        Moderator m1 = moderatorService.getByID(moderator.getId());

        Information i1 = informationService.getByID(m1.getInformation().getId());

        Assertions.assertTrue(information.equals(i1));

    }

    @Test
    public void getByInformation() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);

        Moderator m1 = moderatorService.getByInformation(information);

        Assertions.assertEquals(m1, moderator);
    }
    @Test
    public void updateWithDtoTest() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        Information information2 = informationCreator.getInformation("ROLE_MODERATOR");
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);
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