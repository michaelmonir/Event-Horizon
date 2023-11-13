package com.EventHorizon.EventHorizon.security.controller;

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
    public ResponseEntity<AuthenticationResponse> basicSignUp(@RequestBody RegisterRequest registerRequest){
        if(proxyService.mailInSystem(registerRequest.getEmail())){
            throw new ExistingMail("Mail : " +registerRequest.getEmail() +" Is Already Used");
        }
        if(proxyService.userNameInSystem(registerRequest.getUserName())){
            throw new ExistingMail("UserName : " +registerRequest.getUserName() +" Is Already Used");
        }
        return new ResponseEntity<AuthenticationResponse>(proxyService.signUp(registerRequest),HttpStatus.OK);
    }
    @PostMapping("/basicSignIn")
    public ResponseEntity<AuthenticationResponse> basicSignIn(@RequestBody AuthenticationRequest authenticationRequest){
        return new ResponseEntity<AuthenticationResponse>(proxyService.signIn(authenticationRequest),HttpStatus.OK);
    }

    @PostMapping("/goInWithGmail")
    public ResponseEntity<AuthenticationResponse> goInWithGmail(@RequestBody RegisterRequest registerRequest){
        if(proxyService.mailInSystem(registerRequest.getEmail())){
            throw new ExistingMail("Mail : " +registerRequest.getEmail() +" Is Already Used");
        }else
            return new ResponseEntity<AuthenticationResponse>(proxyService.signUp(registerRequest),HttpStatus.OK);
    }

}
