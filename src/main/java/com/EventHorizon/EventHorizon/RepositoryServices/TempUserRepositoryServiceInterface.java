package com.EventHorizon.EventHorizon.RepositoryServices;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.ClientInformationRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempUserRepositoryServiceInterface
{
    @Autowired
    private InformationRepositoryService informationRepositoryService;
    @Autowired
    private ClientInformationRepositoryService clientInformationRepositoryService;

    public Client getClientByClientInformationId(int clientInformationId) {
        Information information = this.informationRepositoryService.getByID(clientInformationId);
        return (Client)this.clientInformationRepositoryService.getUserByInformation(information);
    }
}
