package com.EventHorizon.EventHorizon.Services.UserServices;

import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepositoryService userRepositoryService;

//    public UserViewDTO updateUser(UserUpdateDTO userUpdateDTO) {
//
//    }
}
