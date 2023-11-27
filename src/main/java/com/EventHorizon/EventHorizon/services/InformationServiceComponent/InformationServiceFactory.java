package com.EventHorizon.EventHorizon.services.InformationServiceComponent;

import com.EventHorizon.EventHorizon.Exceptions.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class InformationServiceFactory{

    @Autowired
    private ClientInformationService clientInformationService;
    @Autowired
    private ModeratorInformationService moderatorInformationService;
    @Autowired
    private OrganizerInformationService organizerInformationService;
    @Autowired
    private SponsorInformationService sponsorInformationService;

    public UserInformationService getUserInformationServiceByRole(String role)
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
