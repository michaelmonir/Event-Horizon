package com.EventHorizon.EventHorizon.Services.UpdatedUserService;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UpdatedUserRepositoryService;
import com.EventHorizon.EventHorizon.security.Service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatedAdminService {

//    @Autowired
//    private UpdatedUserRepositoryService updatedUserRepositoryService;
////    @Autowired
////    private ProxyService proxyService;
//    @Autowired
//    pr
//
//
//    public Information addModerator(UpdatedUserDto registerRequest) {
//        proxyService.removeIfNotEnabled(registerRequest.getEmail());
//        UpdatedUser information = proxyService.createUpdatedUser(registerRequest);
//        information.setActive(1);
//        information.setEnable(1);
//        updatedUserRepositoryService.add(information);
//        return information;
//    }
//
//    public void deleteModerator(int idOfInformation) {
//        Information information = informationRepositoryService.getByID(idOfInformation);
//        moderatorInformationRepositoryService.delete(information);
//    }
}
