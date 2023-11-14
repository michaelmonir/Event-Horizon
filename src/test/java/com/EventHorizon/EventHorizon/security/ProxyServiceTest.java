package com.EventHorizon.EventHorizon.security;
import com.EventHorizon.EventHorizon.DTO.InformationDTO;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.repository.InformationRepository;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationRequest;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationResponse;
import com.EventHorizon.EventHorizon.security.controller.ProxyService;
import com.EventHorizon.EventHorizon.security.execptions.ExistingMail;
import com.EventHorizon.EventHorizon.security.execptions.ExistingUserName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProxyServiceTest {
    @Autowired
    ProxyService proxyService;
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Test
    public void mailInSystemTest(){
        assertEquals(proxyService.mailInSystem("ahmed@gmail.com"),false);
    }
    @Test
    public void mailInSystemTest2(){
        Information information= Information.builder().email("ahmed@gmail.com").password("password").userName("ahmed").role("ROLE_ADMIN").active(1).build();
        informationRepository.save(information);
        assertEquals(proxyService.mailInSystem("ahmed@gmail.com"),true);
    }
    @Test
    public void userNameInSystemTest(){
        assertEquals(proxyService.userNameInSystem("ahmed2"),false);
    }
    @Test
    public void userNameInSystemTest2(){
        Information information= Information.builder().email("ahmed2@gmail.com").password("password").userName("ahmed2").role("ROLE_ADMIN").active(1).build();
        informationRepository.save(information);
        assertEquals(proxyService.userNameInSystem("ahmed2"),true);
    }
    @Test
    public void signUpTest2(){
        InformationDTO request= InformationDTO.builder().email("ahmed4@gmail.com").password("password").userName("ahmed4").role("ROLE_ADMIN").build();
        AuthenticationResponse token=proxyService.signUp(request);
        assertEquals(jwtService.extractUserName(token.getToken()),"ahmed4@gmail.com");
    }
    @Test
    public void signUpTest3(){
        InformationDTO request= InformationDTO.builder().email("ahmed5@gmail.com").password("password").userName("ahmed5").role("ROLE_ADMIN").build();
        AuthenticationResponse token=proxyService.signUp(request);
        assertEquals(jwtService.extractUserName(token.getToken()),"ahmed5@gmail.com");
    }
    @Test
    public void signUpTest4(){
        InformationDTO request= InformationDTO.builder().email("ahmed6@gmail.com").password("password").userName("ahmed6").role("ROLE_ADMIN").build();
        AuthenticationResponse token=proxyService.signUp(request);
        assertEquals(jwtService.extractUserName(token.getToken()),"ahmed6@gmail.com");
    }
    @Test
    public void signUpTest5(){
        InformationDTO request= InformationDTO.builder().email("ahmed7@gmail.com").password("password").userName("ahmed7").role("ROLE_ADMIN").build();
        AuthenticationResponse token=proxyService.signUp(request);
        assertEquals(jwtService.extractUserName(token.getToken()),"ahmed7@gmail.com");
    }

    @Test
    public void signUpExistingMailTest(){
        Information information= Information.builder().email("ahmed8@gmail.com").password("password").userName("ahmed8").role("ROLE_ADMIN").active(1).build();
        informationRepository.save(information);
        Assertions.assertThrows(
            ExistingMail.class, () -> {
                InformationDTO request= InformationDTO.builder().email("ahmed8@gmail.com").password("password").userName("ahmed8").role("ROLE_ADMIN").build();
                AuthenticationResponse token=proxyService.signUp(request);
            }
        );
    }
    @Test
    public void signUpExistingUserNameTest(){
        Information information= Information.builder().email("ahmed9@gmail.com").password("password").userName("ahmed9").role("ROLE_ADMIN").active(1).build();
        informationRepository.save(information);
        Assertions.assertThrows(
                ExistingUserName.class, () -> {
                    InformationDTO request= InformationDTO.builder().email("ahmed10@gmail.com").password("password").userName("ahmed9").role("ROLE_ADMIN").build();
                    AuthenticationResponse token=proxyService.signUp(request);
                }
        );
    }
}
