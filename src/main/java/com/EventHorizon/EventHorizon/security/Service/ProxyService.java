package com.EventHorizon.EventHorizon.security.Service;

import com.EventHorizon.EventHorizon.DTOs.UserDto.InformationDTO;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.Entities.enums.Gender;
import com.EventHorizon.EventHorizon.Entities.enums.Role;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.MailSender.EmailSenderService;
import com.EventHorizon.EventHorizon.RepositoryServices.InformationComponent.InformationRepositoryService;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationRequest;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationResponse;
import com.EventHorizon.EventHorizon.security.authenticationMessages.VerifyRequest;
import com.EventHorizon.EventHorizon.Exceptions.Securiity.ExistingMail;
import com.EventHorizon.EventHorizon.Exceptions.Securiity.ExistingUserName;
import com.EventHorizon.EventHorizon.Exceptions.Securiity.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class ProxyService {

    private final InformationRepositoryService informationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSenderService emailSenderService;

    public boolean mailInSystem(String mail) {
        try {
            informationService.getByEmail(mail);
            return true;
        } catch (InformationNotFoundException e) {
            return false;
        }

    }

    public boolean userNameInSystem(String userName) {
        try {
            informationService.getByUserName(userName);
            return true;
        } catch (InformationNotFoundException e) {
            return false;
        }
    }

    public void removeIfNotEnabled(String mail) {
        if (informationService.existsByEmail(mail)) {
            Information information = informationService.getByEmail(mail);
            if (information.getEnable() == 0) {
                informationService.delete(information.getId());
            }
        }
    }

    public void handleException(String mail, String userName) {
        if (mailInSystem(mail)) {
            throw new ExistingMail();
        }
        if (userNameInSystem(userName)) {
            throw new ExistingUserName();
        }
    }

    private String createCode() {
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 6; i++) {
            char c = (char) (r.nextInt(9) + '0');
            code.append(c);
        }
        return code.toString();
    }

    String generateTokenForSignUp(Information information, String verifyCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("verifyCode", verifyCode);
        map.put("id", information.getId());
        return jwtService.generateToken(map, information);
    }
    String generateTokenForSignIn(Information information) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", information.getId());
        return jwtService.generateToken(map, information);
    }
    public AuthenticationResponse signUp(InformationDTO registerRequest) {
        removeIfNotEnabled(registerRequest.getEmail());
        handleException(registerRequest.getEmail(), registerRequest.getUserName());
        Information information = createInformation(registerRequest);
        String verifyCode = createCode();
        informationService.add(information);
        String jwt = generateTokenForSignUp(information, verifyCode);
        System.out.println(jwtService.extractVerifyCode(jwt));

        if (registerRequest.getSignInWithEmail() == 1) {
            information.setActive(1);
            information.setEnable(1);
        } else {
            emailSenderService.sendMail(information.getEmail(),
                    "Verification Code", information.userName, verifyCode);
        }
        return AuthenticationResponse.builder()
                .id(information.getId())
                .token(jwt)
                .role(information.getRole().toString())
                .build();
    }

    public Information createInformation(InformationDTO registerRequest) {
        return Information.builder()
                .userName(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.fromString(registerRequest.getRole()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender(Gender.fromString(registerRequest.getGender()))
                .payPalAccount(registerRequest.getPayPalAccount())
                .active(0)
                .signInWithEmail(registerRequest.getSignInWithEmail())
                .build();
    }

    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest, int withGmail) {
        Information information = informationService.getByEmail(authenticationRequest.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        if (information.getEnable() == 0) {
            throw new ForbiddenException();
        }
        if (information.getSignInWithEmail() != withGmail) {
            throw new ForbiddenException();
        }
        String jwt = generateTokenForSignIn(information);

        return AuthenticationResponse.builder()
                .id(information.getId())
                .token(jwt)
                .role(information.getRole().toString())
                .build();
    }

    private void putEnable(String mail) {
        Information information = informationService.getByEmail(mail);
        information.setEnable(1);
        information.setActive(1);
        informationService.update(information.getId(), information);
    }

    public Boolean verifyCode(VerifyRequest verifyRequest) {
        Boolean isEqual = verifyRequest.getVerifyCode().equals(jwtService.extractVerifyCode(verifyRequest.getToken()));
        if (isEqual) {
            putEnable(jwtService.extractUserName(verifyRequest.getToken()));
        }
        return isEqual;
    }

}
