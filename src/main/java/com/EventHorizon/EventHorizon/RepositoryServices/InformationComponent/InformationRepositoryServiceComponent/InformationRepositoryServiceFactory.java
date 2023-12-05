package com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent;

import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class InformationRepositoryServiceFactory {

    @Autowired
    private ClientInformationRepositoryService clientInformationService;
    @Autowired
    private ModeratorInformationRepositoryService moderatorInformationService;
    @Autowired
    private OrganizerInformationRepositoryService organizerInformationService;
    @Autowired
    private SponsorInformationRepositoryService sponsorInformationService;

    public UserInformationRepositoryService getUserInformationServiceByRole(String role)
    {
        if (role.equals("ROLE_CLIENT"))
            return this.clientInformationService;
        else if (role.equals("ROLE_MODERATOR"))
            return this.moderatorInformationService;
        else if (role.equals("ROLE_ORGANIZER"))
            return this.organizerInformationService;
        else if (role.equals("ROLE_SPONSOR"))
            return this.sponsorInformationService;
        else
            throw new RoleNotFoundException();
    }

}
