package com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet;


import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.RoleNotFoundException;
import com.EventHorizon.EventHorizon.Repository.UpdatedUserRepositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UpdatedUserRepositoryFactory {

    private final UpdatedClientRepository updatedClientRepository;
    private final UpdatedSponsorRepository updatedSponsorRepository;
    private final UpdatedAdminRepository updatedAdminRepository;
    private final UpdatedModeratorRepository updatedModeratorRepository;
    private final UpdatedOrganizerRepository updatedOrganizerRepository;

    public JpaRepository getRepository(UpdatedUser updatedUser) {
        return switch (updatedUser.getRole()) {
            case CLIENT -> updatedClientRepository;
            case SPONSOR -> updatedSponsorRepository;
            case ADMIN -> updatedAdminRepository;
            case MODERATOR -> updatedModeratorRepository;
            case ORGANIZER -> updatedOrganizerRepository;
            default -> throw new RoleNotFoundException();
        };
    }
}
