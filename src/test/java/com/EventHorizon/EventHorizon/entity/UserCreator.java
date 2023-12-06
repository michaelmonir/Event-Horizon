package com.EventHorizon.EventHorizon.entity;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.User;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.InformationRepositoryServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreator
{
    @Autowired
    private InformationCreator informationCreator;
    @Autowired
    private InformationRepositoryServiceFactory informationRepositoryServiceFactory;

//    public User getUser(Role role)
//    {
////        Information information = informationCreator.getInformation(role);
////        InformationRepositoryService informationRepositoryService
////                = informationRepositoryServiceFactory.getUserInformationServiceByRole(role);
//
//    }
}
