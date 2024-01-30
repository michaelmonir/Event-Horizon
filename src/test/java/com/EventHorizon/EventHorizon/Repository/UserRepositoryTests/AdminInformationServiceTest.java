package com.EventHorizon.EventHorizon.Repository.UserRepositoryTests;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdateDTO;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Admin;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.NotAdminOperationException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.UserNotFoundException;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class AdminInformationServiceTest {

    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private UserCustomCreator userCustomCreator;

    @Test
    public void updateAdminDataTest() {
        Admin admin = (Admin) userCustomCreator.getUser(Role.ADMIN);

        admin.setGender(Gender.NONE);
        userRepositoryService.add(admin);
        admin.setFirstName("newFirstName");
        admin.setLastName("newLastName");
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(admin);
        userRepositoryService.updateWithDto(userUpdateDTO);
        assertEquals(admin, userRepositoryService.getAdminById(admin.getId()));
    }

    @Test
    public void noAdminExceptionTest() {
        Admin admin = (Admin) userCustomCreator.getUser(Role.ADMIN);
        admin.setGender(Gender.NONE);

        userRepositoryService.add(admin);
        userRepositoryService.deleteById(admin.getId());
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(admin);
        assertThrows(
                UserNotFoundException.class, () -> {
                    userRepositoryService.updateWithDto(userUpdateDTO);
                }
        );
    }

    @Test
    public void NotAdminOperationExceptionTest() {
        Admin admin = (Admin) userCustomCreator.getUser(Role.ADMIN);
        admin.setGender(Gender.NONE);
        assertThrows(
                NotAdminOperationException.class, () -> {
                    userRepositoryService.add(admin);
                }
        );
    }

}
