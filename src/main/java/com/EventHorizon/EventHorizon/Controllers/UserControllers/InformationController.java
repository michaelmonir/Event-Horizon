package com.EventHorizon.EventHorizon.Controllers.UserControllers;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserUpdateDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UserViewDTO;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/information/")
@RestController
@CrossOrigin("*")

public class InformationController {
    @Autowired
    private UserRepositoryService informationService;
    @GetMapping("getInformationViewDto")
    public ResponseEntity<UserViewDTO>getInformation(@RequestParam Integer id){
        return new ResponseEntity<UserViewDTO>(new UserViewDTO(informationService.getById(id)), HttpStatus.OK);
    }
    @GetMapping("getInformationUpdateDto")
    public ResponseEntity<UserUpdateDTO>getInformationUpdateDto(@RequestParam Integer id){
        return new ResponseEntity<UserUpdateDTO>(new UserUpdateDTO(informationService.getById(id)), HttpStatus.OK);
    }
    @PutMapping("updateInformation")
    public ResponseEntity<UserViewDTO>updateInformation(@RequestBody UserUpdateDTO userUpdateDTO){
        return new ResponseEntity<UserViewDTO>(informationService.updateWithDto(userUpdateDTO), HttpStatus.OK);
    }
}
