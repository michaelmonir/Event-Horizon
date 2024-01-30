package com.EventHorizon.EventHorizon.Services.UpdatedUserService;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.UpdatedUser;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UpdatedUserMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UpdatedUserRepositoryService;
import com.EventHorizon.EventHorizon.security.Service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdatedAdminService {

    @Autowired
    private UpdatedUserRepositoryService updatedUserRepositoryService;
    @Autowired
    private ProxyService proxyService;
    @Autowired
    private UpdatedUserMapper updatedUserMapper;


    public UpdatedUser addModerator(UpdatedUserDto registerRequest) {
        proxyService.removeIfNotEnabled(registerRequest.getEmail());
        UpdatedUser user = updatedUserMapper.createUser(registerRequest);
        user.setActive(1);
        user.setEnable(1);
        updatedUserRepositoryService.add(user);
        return user;
    }

    public void deleteModerator(int id) {
        updatedUserRepositoryService.deleteById(id);
    }
}
