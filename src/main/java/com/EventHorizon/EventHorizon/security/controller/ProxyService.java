package com.EventHorizon.EventHorizon.security.controller;

import com.EventHorizon.EventHorizon.DTO.InformationDTO;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.security.JwtService;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationRequest;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationResponse;
import com.EventHorizon.EventHorizon.security.execptions.ExistingMail;
import com.EventHorizon.EventHorizon.security.execptions.ExistingUserName;
import com.EventHorizon.EventHorizon.services.InformationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProxyService {
    private final InformationService informationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public boolean mailInSystem(String mail){
        Information information=informationService.getByEmail(mail);
        return information!=null ;
    }
    public boolean userNameInSystem(String userName){
        Information information=informationService.getByUserName(userName);
        return information!=null;
    }
    public AuthenticationResponse signUp(InformationDTO registerRequest) {
        if(mailInSystem(registerRequest.getEmail())){
            throw new ExistingMail("Mail : " +registerRequest.getEmail() +" Is Already Used");
        }
        if(userNameInSystem(registerRequest.getUserName())){
            throw new ExistingUserName("UserName : " +registerRequest.getUserName() +" Is Already Used");
        }
        Information information=Information.builder()
                .userName(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(registerRequest.getRole())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender(registerRequest.getGender())
                .payPalAccount(registerRequest.getPayPalAccount())
                .active(1)
                .build();
        informationService.add(information);
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
        Information information =informationService.getByEmail(authenticationRequest.getEmail());
        String jwt=jwtService.generateToken(information);
        return AuthenticationResponse.builder().token(jwt).build();
    }

}
