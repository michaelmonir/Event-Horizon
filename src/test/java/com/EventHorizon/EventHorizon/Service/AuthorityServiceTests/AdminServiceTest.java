package com.EventHorizon.EventHorizon.Service.AuthorityServiceTests;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.InformationCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.Securiity.ExistingMail;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.ModeratorNotFoundException;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.Services.UserServices.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceTest {


    @Autowired
    InformationCustomCreator informationCreator;
    @Autowired
    AdminService adminService;

    @Autowired
    InformationRepositoryService informationRepositoryService;

    @Test
    public void addModeratorTestIfSucceed() {
        Information information = informationCreator.getInformation(Role.MODERATOR);
        information.setPassword("pass12345");
        UpdatedUserDto updatedUserDto = new UpdatedUserDto(information);
        Information information2=adminService.addModerator(updatedUserDto);
        Moderator moderator = (Moderator) informationRepositoryService.getUserByInformation(information2);
        Assertions.assertEquals(moderator.getInformation().getId(), information2.getId());
    }

    @Test
    public void addModeratorTestIfFail() {
        Information information = informationCreator.getInformation(Role.MODERATOR);
        information.setPassword("pass12345");
        UpdatedUserDto updatedUserDto = new UpdatedUserDto(information);
        adminService.addModerator(updatedUserDto);

        Information information2 = informationCreator.getInformation(Role.MODERATOR);
        information2.setPassword("pass12345");
        UpdatedUserDto updatedUserDto2 = new UpdatedUserDto(information2);
        updatedUserDto2.setEmail(updatedUserDto.getEmail());
        Assertions.assertThrows(
                ExistingMail.class, () -> {
                    adminService.addModerator(updatedUserDto);
                }
        );
    }

    @Test
    public void deleteModeratorTestIfSucceed() {
        Information information = informationCreator.getInformation(Role.MODERATOR);
        information.setPassword("pass12345");
        UpdatedUserDto updatedUserDto = new UpdatedUserDto(information);
        Information information2 = adminService.addModerator(updatedUserDto);
        adminService.deleteModerator(information2.getId());

        Assertions.assertThrows(
                ModeratorNotFoundException.class, () -> {
                   informationRepositoryService.getUserByInformation(information2);
                }
        );

    }

    @Test
    public void deleteModeratorTestIfFail() {
        Assertions.assertThrows(
                InformationNotFoundException.class, () -> {
                    adminService.deleteModerator(1000000);
                }
        );

    }
}
