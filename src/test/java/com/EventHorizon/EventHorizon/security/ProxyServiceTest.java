package com.EventHorizon.EventHorizon.security;
import com.EventHorizon.EventHorizon.DTO.InformationDTO;
import com.EventHorizon.EventHorizon.entity.Information;
import com.EventHorizon.EventHorizon.repository.InformationRepository;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationRequest;
import com.EventHorizon.EventHorizon.security.authenticationMessages.AuthenticationResponse;
import com.EventHorizon.EventHorizon.security.controller.ProxyService;
import com.EventHorizon.EventHorizon.services.InformationService;
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
    public void signUpTest(){
        InformationDTO request= InformationDTO.builder().email("ahmed4@gmail.com").password("password").userName("ahmed4").role("ROLE_ADMIN").build();
        AuthenticationResponse token=proxyService.signUp(request);
        assertEquals(jwtService.extractUserName(token.getToken()),"ahmed4@gmail.com");
    }

}
