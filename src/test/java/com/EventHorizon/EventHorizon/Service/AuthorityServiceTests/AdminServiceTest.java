package com.EventHorizon.EventHorizon.Service.AuthorityServiceTests;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Moderator;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.User.AlreadyFoundException;
import com.EventHorizon.EventHorizon.Exceptions.User.InvalidUserDataException;
import com.EventHorizon.EventHorizon.Exceptions.User.UserNotFoundException;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.UserServices.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private UserCustomCreator userCustomCreator;
    @Autowired
    private AdminService adminService;
    @Autowired
    private GetUserRepositoryService getUserRepositoryService;

    @Autowired
    UserRepositoryService userRepositoryService;

    @Test
    public void addModeratorTestIfSucceed() {
        Moderator moderator = (Moderator) userCustomCreator.getUser(Role.MODERATOR);
        moderator.setPassword("pass12345");

        UpdatedUserDto updatedUserDto = new UpdatedUserDto(moderator);

        Moderator moderator1 = adminService.addModerator(updatedUserDto);
        Moderator moderator2 = getUserRepositoryService.getModeratorById(moderator1.getId());
        Assertions.assertEquals(moderator2, moderator1);
    }

    @Test
    public void addModeratorTestIfFail() {
        Moderator moderator = (Moderator) userCustomCreator.getUser(Role.MODERATOR);
        moderator.setPassword("pass12345");

        UpdatedUserDto updatedUserDto = new UpdatedUserDto(moderator);

        adminService.addModerator(updatedUserDto);

        Moderator information2 = (Moderator) userCustomCreator.getUser(Role.MODERATOR);
        information2.setPassword("pass12345");
        UpdatedUserDto updatedUserDto2 = new UpdatedUserDto(information2);
        updatedUserDto2.setEmail(updatedUserDto.getEmail());
        Assertions.assertThrows(
                InvalidUserDataException.class, () -> adminService.addModerator(updatedUserDto)
        );
    }

    @Test
    public void deleteModeratorTestIfSucceed() {
        Moderator moderator = (Moderator) userCustomCreator.getUser(Role.MODERATOR);
        moderator.setPassword("pass12345");

        UpdatedUserDto updatedUserDto = new UpdatedUserDto(moderator);

        Moderator information2 = adminService.addModerator(updatedUserDto);
        adminService.deleteModerator(information2.getId());

        Assertions.assertThrows(
                UserNotFoundException.class, () -> getUserRepositoryService.getById(information2.getId())
        );

    }

    @Test
    public void deleteModeratorTestIfFail() {
        Assertions.assertThrows(
                UserNotFoundException.class, () -> {
                    adminService.deleteModerator(1000000);
                }
        );

    }
}
