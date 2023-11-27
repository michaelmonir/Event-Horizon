package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ModeratorNotFoundException;
import com.EventHorizon.EventHorizon.services.InformationService;
import com.EventHorizon.EventHorizon.services.ModeratorService;
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
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);

        Moderator m1 = moderatorService.getByID(moderator.getId());
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
    public void update() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        Moderator moderator = Moderator.builder().information(information).build();
        moderatorService.add(moderator);
        Information information2 = informationCreator.getInformation("ROLE_MODERATOR");
        Moderator moderator2 = Moderator.builder().information(information2).build();
        moderatorService.update(moderator.getId(), moderator2);
        Moderator m1 = moderatorService.getByID(moderator.getId());
        Information i1 = informationService.getByID(m1.getInformation().getId());

        Assertions.assertTrue(information2.equals(i1));
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
}