package com.EventHorizon.EventHorizon.security.controller;

import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.repository.InformationRepository;
import com.EventHorizon.EventHorizon.security.JwtService;
import com.EventHorizon.EventHorizon.security.execptions.ExistingMail;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProxyService {
    private final InformationRepository informationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    boolean mailInSystem(String mail){
        Optional<Information> information=informationRepository.findByEmail(mail);
        return information.isPresent() ;
    }
    boolean userNameInSystem(String userName){
        Optional<Information> information=informationRepository.findByUserName(userName);
        return information.isPresent();
    }
    public AuthenticationResponse signUp(RegisterRequest registerRequest) {
        Information information=Information.builder().
                userName(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(registerRequest.getRole())
                .active(1)
                .build();
        informationRepository.save(information);
        String jwt=jwtService.generateToken(information);
        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(
                     authenticationRequest.getEmail(),
                     authenticationRequest.getPassword()
             )
        );
        Information information =informationRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        String jwt=jwtService.generateToken(information);
        return AuthenticationResponse.builder().token(jwt).build();
    }
    public AuthenticationResponse signInWithGmail(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        Information information =informationRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        String jwt=jwtService.generateToken(information);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
