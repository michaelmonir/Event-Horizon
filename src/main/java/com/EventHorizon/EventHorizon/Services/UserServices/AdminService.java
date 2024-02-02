package com.EventHorizon.EventHorizon.Services.UserServices;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Moderator;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UserMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.security.Service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private ProxyService proxyService;
    @Autowired
    private UserMapper userMapper;


    public Moderator addModerator(UpdatedUserDto registerRequest) {
        proxyService.removeIfNotEnabled(registerRequest.getEmail());
        Moderator moderator = (Moderator) userMapper.createUser(registerRequest);
        moderator.setActive(1);
        moderator.setEnable(1);
        userRepositoryService.create(moderator);
        return moderator;
    }

    public void deleteModerator(int id) {
        userRepositoryService.deleteById(id);
    }
}
