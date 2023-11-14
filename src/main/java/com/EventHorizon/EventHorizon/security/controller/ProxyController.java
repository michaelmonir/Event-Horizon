package com.EventHorizon.EventHorizon.security.controller;

import com.EventHorizon.EventHorizon.DTO.InformationDTO;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationRequest;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationResponse;
import com.EventHorizon.EventHorizon.security.execptions.ExistingMail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proxy")
@RequiredArgsConstructor
public class ProxyController {

    private final ProxyService proxyService;

    @PostMapping("/basicSignUp")
    public ResponseEntity<AuthenticationResponse> basicSignUp(@RequestBody InformationDTO registerRequest){
        return new ResponseEntity<AuthenticationResponse>(proxyService.signUp(registerRequest),HttpStatus.OK);
    }
    @PostMapping("/basicSignIn")
    public ResponseEntity<AuthenticationResponse> basicSignIn(@RequestBody AuthenticationRequest authenticationRequest){
        return new ResponseEntity<AuthenticationResponse>(proxyService.signIn(authenticationRequest),HttpStatus.OK);
    }
    @PostMapping("/signUpWithGmail")
    public ResponseEntity<AuthenticationResponse> signUpWithGmail(@RequestBody InformationDTO registerRequest){

        return new ResponseEntity<AuthenticationResponse>(proxyService.signUp(registerRequest),HttpStatus.OK);
    }
    @PostMapping("/signInWithGmail")
    public ResponseEntity<AuthenticationResponse> signInWithGmail(@RequestBody AuthenticationRequest authenticationRequest){
        return new ResponseEntity<AuthenticationResponse>(proxyService.signIn(authenticationRequest),HttpStatus.OK);
    }

}
