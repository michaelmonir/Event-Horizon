package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent;

import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InformationRepositoryServiceFactory {

    private final ClientInformationRepositoryService clientInformationService;
    private final ModeratorInformationRepositoryService moderatorInformationService;
    private final OrganizerInformationRepositoryService organizerInformationService;
    private final SponsorInformationRepositoryService sponsorInformationService;
    private final AdminInformationRepositoryService adminInformationService;
    public SuperUserInformationRepositoryService getUserInformationServiceByRole(String role) {
        return switch (role) {
            case "ROLE_CLIENT" -> this.clientInformationService;
            case "ROLE_MODERATOR" -> this.moderatorInformationService;
            case "ROLE_ORGANIZER" -> this.organizerInformationService;
            case "ROLE_SPONSOR" -> this.sponsorInformationService;
            case "ROLE_ADMIN" -> this.adminInformationService;
            default -> throw new RoleNotFoundException();
        };
    }

}
