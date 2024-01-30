package com.EventHorizon.EventHorizon.security.Service;

import com.EventHorizon.EventHorizon.DTOs.UserDto.UpdatedUserDto;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.User;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.UserNotFoundException;
import com.EventHorizon.EventHorizon.MailSender.EmailSenderService;
import com.EventHorizon.EventHorizon.Mappers.UpdatedUser.UserMapper;
import com.EventHorizon.EventHorizon.RepositoryServices.UpdatedUserComponenet.UserRepositoryService;
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

    private final UserRepositoryService userRepositoryService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSenderService emailSenderService;
    private final UserMapper userMapper;

    public boolean mailInSystem(String mail) {
        try {
            userRepositoryService.getByEmail(mail);
            return true;
        } catch (InformationNotFoundException e) {
            return false;
        }

    }

    public boolean userNameInSystem(String userName) {
        try {
            userRepositoryService.getByUserName(userName);
            return true;
        } catch (InformationNotFoundException e) {
            return false;
        }
    }

    public void removeIfNotEnabled(String mail) {
        try {
            User user = userRepositoryService.getByEmail(mail);
            if (user.getEnable() == 0) {
                userRepositoryService.delete(user);
            }
        } catch (UserNotFoundException e) {

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

    String generateTokenForSignUp(User user, String verifyCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("verifyCode", verifyCode);
        map.put("id", user.getId());
        return jwtService.generateToken(map, user);
    }

    String generateTokenForSignIn(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        return jwtService.generateToken(map, user);
    }

    private AuthenticationResponse createAuthenticationResponse(User user, String jwt) {
        return AuthenticationResponse.builder()
                .id(user.getId())
                .token(jwt)
                .role(user.getRole().toString())
                .build();
    }

    public AuthenticationResponse signUp(UpdatedUserDto registerRequest) {
        removeIfNotEnabled(registerRequest.getEmail());
        handleException(registerRequest.getEmail(), registerRequest.getUserName());
        User user = userMapper.createUser(registerRequest);
        String verifyCode = createCode();
        userRepositoryService.add(user);
        String jwt = generateTokenForSignUp(user, verifyCode);
        System.out.println(jwtService.extractVerifyCode(jwt));
        if (registerRequest.getSignInWithEmail() == 1) {
            user.setActive(1);
            user.setEnable(1);
        } else {
            emailSenderService.sendMail(user.getEmail(),
                    "Verification Code", user.userName, verifyCode);
        }
        return createAuthenticationResponse(user, jwt);
    }


    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest, int withGmail) {
        User user = userRepositoryService.getByEmail(authenticationRequest.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        if (user.getEnable() == 0) {
            throw new ForbiddenException();
        }
        if (user.getSignInWithEmail() != withGmail) {
            throw new ForbiddenException();
        }
        String jwt = generateTokenForSignIn(user);
        return createAuthenticationResponse(user, jwt);
    }

    private void putEnable(String mail) {
        User user = userRepositoryService.getByEmail(mail);
        user.setEnable(1);
        user.setActive(1);
        userRepositoryService.update(user);
    }

    public Boolean verifyCode(VerifyRequest verifyRequest) {
        Boolean isEqual = verifyRequest.getVerifyCode().equals(jwtService.extractVerifyCode(verifyRequest.getToken()));
        if (isEqual) {
            putEnable(jwtService.extractUserName(verifyRequest.getToken()));
        }
        return isEqual;
    }

}
