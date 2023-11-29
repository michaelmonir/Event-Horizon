package com.EventHorizon.EventHorizon.security;

import com.EventHorizon.EventHorizon.DTOs.UserDto.InformationDTO;
import com.EventHorizon.EventHorizon.Exceptions.UsersExceptions.InformationNotFoundException;
import com.EventHorizon.EventHorizon.MailSender.EmailSenderService;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Information;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationRequest;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationResponse;
import com.EventHorizon.EventHorizon.security.authenticationMessages.VerifyRequest;
import com.EventHorizon.EventHorizon.security.execptions.ExistingMail;
import com.EventHorizon.EventHorizon.security.execptions.ExistingUserName;
import com.EventHorizon.EventHorizon.security.execptions.ForbiddenException;
import com.EventHorizon.EventHorizon.Services.InformationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class ProxyService {
    private final InformationService informationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailSenderService emailSenderService;

    public boolean mailInSystem(String mail) {
        try {
            Information information = informationService.getByEmail(mail);
            return true;
        } catch (InformationNotFoundException e) {
            return false;
        }

    }
    public boolean userNameInSystem(String userName){
        try {
            Information information = informationService.getByUserName(userName);
            return true;
        }
        catch (InformationNotFoundException e) {
            return false;
        }
    }
    public void removeIfNotEnabled(String mail) {
        try {
            Information information = informationService.getByEmail(mail);
            if (information.getEnable() == 0) {
                informationService.delete(information.getId());
            }
        } catch (InformationNotFoundException e) {

        }
    }
    public void handleException(String mail,String userName){
        if(mailInSystem(mail)){
            throw new ExistingMail("Mail : " +mail+" Is Already Used") ;
        }
        if(userNameInSystem(userName)){
            throw new ExistingUserName("UserName : " +userName +" Is Already Used");
        }
    }
    private String createCode(){
        String code="";
        Random r = new Random();
        for (int i=0;i<6;i++)
        {
            char c = (char)(r.nextInt(9) + '0');
            code=code+c;
        }
        return code;
    }
    String generateToken(Information information){
        Map<String,Object> map =new HashMap<>();
        map.put("verifyCode",information.getVerifyCode());
        return jwtService.generateToken(map,information);
    }
    public AuthenticationResponse signUp(InformationDTO registerRequest) {
        removeIfNotEnabled(registerRequest.getEmail());
        handleException(registerRequest.getEmail(),registerRequest.getUserName());
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
                .signInWithEmail(registerRequest.getSignInWithEmail())
                .build();

        information.setVerifyCode(createCode());

        informationService.add(information);
        String jwt=generateToken(information);
        System.out.println(jwtService.extractVerifyCode(jwt));
        if(registerRequest.getSignInWithEmail()==1)
        {
            information.setEnable(1);
        }else{
            emailSenderService.sendMail(information.getEmail(),"Verification Code","Hello\t"+information.userName +"\n\n"+"Thanks for signing up with EventHorizon\n\n" +
                    "To verify your email please use the next code"+"\n\n"+"Verification Code :\t"+information.getVerifyCode()+"\n\n"+"We look forward to see you in next event\n\n"+"Sincerely,\n" +
                    "EventHorizon Team");
        }
        return AuthenticationResponse.builder()
                .id(information.getId())
                .token(jwt)
                .role(information.getRole())
                .build();
    }

    public AuthenticationResponse signIn(AuthenticationRequest authenticationRequest,int withGmail) {
        Information information =informationService.getByEmail(authenticationRequest.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        if(information.getEnable()==0){
            throw new ForbiddenException("Invalid Request");
        }
        if(information.getSignInWithEmail()!=withGmail) {
            throw new ForbiddenException("Invalid Request");
        }
        String jwt=generateToken(information);
        return AuthenticationResponse.builder()
                .id(information.getId())
                .token(jwt)
                .role(information.getRole())
                .build();
    }
    private void putEnable(String mail){
        Information information=informationService.getByEmail(mail);
        information.setEnable(1);
        informationService.update(information.getId(),information);
    }
    public Boolean verifyCode(VerifyRequest verifyRequest) {
        Boolean isEqual=verifyRequest.getVerifyCode().equals(jwtService.extractVerifyCode(verifyRequest.getToken()));
        if(isEqual){
            putEnable(jwtService.extractUserName(verifyRequest.getToken()));
        }
        return isEqual;
    }

}
