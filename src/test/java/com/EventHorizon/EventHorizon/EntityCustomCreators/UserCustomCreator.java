package com.EventHorizon.EventHorizon.EntityCustomCreators;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.InformationRepositoryServiceFactory;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.SuperUserInformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.UserInformationRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCustomCreator {
    @Autowired
    private InformationCustomCreator informationCustomCreator;
    @Autowired
    private InformationRepositoryServiceFactory informationRepositoryServiceFactory;

    public User getUserAndSaveInRepository(Role role) {
        Information information = informationCustomCreator.getInformation(role);
        UserInformationRepositoryService informationRepositoryService
                = (UserInformationRepositoryService) informationRepositoryServiceFactory.getUserInformationServiceByRole(role);
        return informationRepositoryService.add(information);
    }
}
