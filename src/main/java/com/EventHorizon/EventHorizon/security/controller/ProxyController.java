package com.EventHorizon.EventHorizon.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proxy")
public class ProxyController {
    private @Autowired  AuthenticationManager authenticationManager;
    @PostMapping("/basicSignIn")
    public ResponseEntity<Void> basicSignIn(@RequestParam String mail, @RequestParam String pass) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(mail, pass);
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/basicSignUp")
    public ResponseEntity<Void> basicSignUp(@RequestParam String mail, @RequestParam String pass) {
        // put it into Database
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(mail, pass);
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
