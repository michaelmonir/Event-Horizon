package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.DTOs.UserDto.InformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdateInformationDTO;
import com.EventHorizon.EventHorizon.DTOs.UserDto.ViewInformationDTO;
import com.EventHorizon.EventHorizon.Services.AdminService;
import com.EventHorizon.EventHorizon.Services.AuthorityService;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Admin/")
@RestController
@CrossOrigin("*")

public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    AuthorityService authorityService;

    //// ahmed hassan choose response< entity >

/*
    @PostMapping("addModeratorByAdmin")
    public ResponseEntity<> addModeratorByAdmin(@RequestBody InformationDTO registerRequest) {
        return new ResponseEntity<>(adminService.addModerator(registerRequest));
    }

    @DeleteMapping("deleteModeratorByAdmin")
    public ResponseEntity<> deleteModeratorByAdmin(@RequestParam Integer idOfModerator) {
        return new ResponseEntity<>(adminService.deleteModerator(idOfModerator));
    }
 */


}
