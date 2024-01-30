package com.EventHorizon.EventHorizon.Services.UserServices;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UserMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
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


    public User addModerator(UpdatedUserDto registerRequest) {
        proxyService.removeIfNotEnabled(registerRequest.getEmail());
        User user = userMapper.createUser(registerRequest);
        user.setActive(1);
        user.setEnable(1);
        userRepositoryService.add(user);
        return user;
    }

    public void deleteModerator(int id) {
        userRepositoryService.deleteById(id);
    }
}
