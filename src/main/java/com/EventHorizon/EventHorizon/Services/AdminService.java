package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.DTOs.UserDto.InformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Moderator;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryServiceComponent.ModeratorInformationRepositoryService;
import com.EventHorizon.EventHorizon.security.Service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    InformationRepositoryService informationRepositoryService;
    @Autowired
    ModeratorInformationRepositoryService moderatorInformationRepositoryService;
    @Autowired
    ProxyService proxyService;


    public Information addModerator(InformationDTO registerRequest) {
        /* To remove any account if it is not  verified  */
        proxyService.removeIfNotEnabled(registerRequest.getEmail());
        /* if userName used is used in database */
        proxyService.handleException(registerRequest.getEmail(), registerRequest.getUserName());
        Information information = proxyService.createInformation(registerRequest);
        information.setActive(1);
        information.setEnable(1);
        moderatorInformationRepositoryService.add(information);
        return information;
    }

    public void deleteModerator(int idOfInformation) {
        Information information = informationRepositoryService.getByID(idOfInformation);
        moderatorInformationRepositoryService.delete(information);
    }
}
