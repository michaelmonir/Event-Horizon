package com.EventHorizon.EventHorizon.Controllers.UserControllers;

import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Services.UserServices.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/authority/")
@RestController
@CrossOrigin("*")

public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @DeleteMapping("deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam Integer idOfUser) {
        authorityService.deleteUser(idOfUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("deleteEvent")
    public ResponseEntity<?> deleteEvent(@RequestParam Integer idOfEvent) {
        authorityService.deleteEvent(idOfEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getAllUsersByRole")
    public ResponseEntity<?> getAllUsersByRole(@RequestParam Role role) {
        return new ResponseEntity<>(authorityService.getAllUsersByRole(role), HttpStatus.OK);
    }

}
