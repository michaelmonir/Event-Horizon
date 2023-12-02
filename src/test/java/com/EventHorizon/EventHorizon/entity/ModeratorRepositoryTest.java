package com.EventHorizon.EventHorizon.entity;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ModeratorNotFoundException;
import com.EventHorizon.EventHorizon.Services.InformationServiceComponent.ModeratorInformationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ModeratorRepositoryTest {
    @Autowired
    private ModeratorInformationService moderatorInformationService;
    @Autowired
    private InformationCreator informationCreator;

    @Test
    public void addModeratorTest() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        moderatorInformationService.add(information);
        Moderator m1 = (Moderator) moderatorInformationService.getUserByInformation(information);
        Assertions.assertTrue(information.equals(m1.getInformation()));
    }

    @Test
    public void deleteModeratorTest() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        moderatorInformationService.add(information);
        moderatorInformationService.delete(information);

        Assertions.assertThrows(
                ModeratorNotFoundException.class, () -> {
                    moderatorInformationService.getUserByInformation(information);
                }
        );
    }

    @Test
    public void getByInformationModeratorTest() {
        Information information = informationCreator.getInformation("ROLE_MODERATOR");
        moderatorInformationService.add(information);
        Moderator m1 = (Moderator) moderatorInformationService.getUserByInformation(information);

        Assertions.assertEquals(m1.getInformation(), information);
    }
}