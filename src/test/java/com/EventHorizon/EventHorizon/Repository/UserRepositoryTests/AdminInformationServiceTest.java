package com.EventHorizon.EventHorizon.Repository.UserRepositoryTests;

import com.EventHorizon.EventHorizon.Entities.User.Admin;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.EntityCustomCreators.User.UserCustomCreator;
import com.EventHorizon.EventHorizon.Exceptions.User.NotAdminOperationException;
import com.EventHorizon.EventHorizon.Repository.User.UserRepository;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GetUserRepositoryService getUserRepositoryService;

    @Test
    public void updateAdminDataTest() {
//        Admin admin = (Admin) userCustomCreator.getUser(Role.ADMIN);
//
//        admin.setGender(Gender.NONE);
//        userRepository.save(admin);
//        admin.setFirstName("newFirstName");
//        admin.setLastName("newLastName");
//        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(admin);
//        userRepositoryService.updateWithDto(userUpdateDTO);
//        assertEquals(admin.userName, getUserRepositoryService.getAdminById(admin.getId()).userName);
    }

    @Test
    public void noAdminExceptionTest() {
//        Admin admin = (Admin) userCustomCreator.getUser(Role.ADMIN);
//        admin.setGender(Gender.NONE);
//        admin.setId(1000000);
//        UserUpdateDTO userUpdateDTO = new UserUpdateDTO(admin);
//        assertThrows(
//                UserNotFoundException.class, () -> {
//                    userRepositoryService.updateWithDto(userUpdateDTO);
//                }
//        );
    }

    @Test
    public void NotAdminOperationExceptionTest() {
        Admin admin = (Admin) userCustomCreator.getUser(Role.ADMIN);
        admin.setGender(Gender.NONE);
        assertThrows(
                NotAdminOperationException.class, () -> {
                    userRepositoryService.create(admin);
                }
        );
    }

}
