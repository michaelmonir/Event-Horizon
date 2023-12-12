package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent;

import com.EventHorizon.EventHorizon.Entities.enums.Role;
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

    public SuperUserInformationRepositoryService getUserInformationServiceByRole(Role role) {
        return switch (role) {
            case CLIENT -> this.clientInformationService;
            case MODERATOR -> this.moderatorInformationService;
            case ORGANIZER -> this.organizerInformationService;
            case SPONSOR -> this.sponsorInformationService;
            case ADMIN -> this.adminInformationService;
        };
    }
}
