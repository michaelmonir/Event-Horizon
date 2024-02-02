package com.EventHorizon.EventHorizon.Services.UserServices;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.User.NotModeratorOperationsException;
import com.EventHorizon.EventHorizon.RepositoryServices.Event.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    UserRepositoryService userRepositoryService;

    public void deleteUser(int id) {
        Role role = userRepositoryService.getRole(id);
        if (role == Role.MODERATOR)
            throw new NotModeratorOperationsException();
        userRepositoryService.deleteById(id);
    }

    public void deleteEvent(int id) {
        launchedEventRepositoryService.delete(id);
    }

    public List<? extends User> getAllUsersByRole(Role role) {
        return userRepositoryService.findAllByRole(role);
    }

}
