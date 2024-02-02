package com.EventHorizon.EventHorizon.Controllers.UserControllers;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UserViewDTO;
import com.EventHorizon.EventHorizon.RepositoryServices.User.GetUserRepositoryService;
import com.EventHorizon.EventHorizon.RepositoryServices.User.UserRepositoryService;
import com.EventHorizon.EventHorizon.Services.UserServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/information/")
@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private GetUserRepositoryService getUserRepositoryService;
    @Autowired
    private UserService userService;

    @GetMapping("getInformationViewDto")
    public ResponseEntity<UserViewDTO>getInformation(@RequestParam Integer id){
        return new ResponseEntity<UserViewDTO>(new UserViewDTO(getUserRepositoryService.getById(id)), HttpStatus.OK);
    }
    @GetMapping("getInformationUpdateDto")
    public ResponseEntity<UserUpdationDTO>getInformationUpdateDto(@RequestParam Integer id){
        return new ResponseEntity<UserUpdationDTO>(new UserUpdationDTO(getUserRepositoryService.getById(id)), HttpStatus.OK);
    }
    @PutMapping("updateInformation")
    public ResponseEntity<UserViewDTO>updateInformation(@RequestBody UserUpdationDTO userUpdationDTO){
        return new ResponseEntity<>(userService.updateUser(userUpdationDTO), HttpStatus.OK);
    }
}
