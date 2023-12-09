package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Services.AuthorityService;
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

    @GetMapping("getAllUsersByRole")
    public ResponseEntity<?> getAllUsersByRole(@RequestParam Role role) {
        return new ResponseEntity<>(authorityService.getAllUsersByRole(role), HttpStatus.OK);
    }

    @GetMapping("getAllUsersByFirstName")
    public ResponseEntity<?> getAllUsersByFirstName(@RequestParam String firstName) {
        return new ResponseEntity<>(authorityService.getAllUsersByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("getAllUsersByLastName")
    public ResponseEntity<?> getAllUsersByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(authorityService.getAllUsersByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("getAllUsersByGender")
    public ResponseEntity<?> getAllUsersByRole(@RequestParam Gender gender) {
        return new ResponseEntity<>(authorityService.getAllUsersByGender(gender), HttpStatus.OK);
    }

    @GetMapping("getUserByEmail")
    public ResponseEntity<?> getAllUsersByEmail(@RequestParam String email) {
        return new ResponseEntity<>(authorityService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("getUserByUserName")
    public ResponseEntity<?> getAllUsersByUserName(@RequestParam String userName) {
        return new ResponseEntity<>(authorityService.getUserByUserName(userName), HttpStatus.OK);
    }


}
