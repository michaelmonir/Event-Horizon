package com.EventHorizon.EventHorizon.Controllers;

import com.EventHorizon.EventHorizon.Services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Authority/")
@RestController
@CrossOrigin("*")

public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    /*
    @DeleteMapping("deleteUser")
    public ResponseEntity<> delete(@RequestParam Integer idOfUser) {
        return new ResponseEntity<>(authorityService.deleteUser(idOfUser));
    }
    */
    ///  to be continue

}
