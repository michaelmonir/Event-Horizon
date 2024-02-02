package com.EventHorizon.EventHorizon.Controllers.UserControllers;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UserCreationDto;
import com.EventHorizon.EventHorizon.Services.UserServices.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/")
@RestController
@CrossOrigin("*")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("addModerator")
    public ResponseEntity<?> addModerator(@RequestBody UserCreationDto registerRequest) {
        adminService.addModerator(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("deleteModerator")
    public ResponseEntity<?> deleteModerator(@RequestParam Integer idOfModerator) {
        adminService.deleteModerator(idOfModerator);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
