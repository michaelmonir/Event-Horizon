package com.EventHorizon.EventHorizon.Services.UserServices;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UserViewDTO;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Mappers.User.UserMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private GetUserRepositoryService getUserRepositoryService;
    @Autowired
    private UserMapper userMapper;

    public UserViewDTO updateUser(UserUpdationDTO updationDTO) {
        User user = getUserRepositoryService.getById(updationDTO.getId());
        userMapper.updateUser(user, updationDTO);
        return new UserViewDTO(user);
    }
}
