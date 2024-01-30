package com.EventHorizon.EventHorizon.Services.UpdatedUserService;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.NotModeratorOperationsException;
import com.EventHorizon.EventHorizon.RepositoryServices.EventComponent.EventRepositoryServices.LaunchedEventRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.UserInformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UpdatedUserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UpdatedAuthorityService {

    @Autowired
    LaunchedEventRepositoryService launchedEventRepositoryService;
    @Autowired
    UpdatedUserRepositoryService updatedUserRepositoryService;

    public void deleteUser(int id) {
        Role role = updatedUserRepositoryService.getRole(id);
        if (role == Role.MODERATOR)
            throw new NotModeratorOperationsException();
        updatedUserRepositoryService.deleteById(id);
    }

    public void deleteEvent(int id) {
        launchedEventRepositoryService.delete(id);
    }

    public List<? extends UpdatedUser> getAllUsersByRole(Role role) {
        return updatedUserRepositoryService.findAllByRole(role);
    }

}
