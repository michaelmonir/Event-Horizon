package com.EventHorizon.EventHorizon;

import com.EventHorizon.EventHorizon.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class DemoController {

    @GetMapping("/hello")
    ResponseEntity<String>hello(){
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }
    @GetMapping("/hello2")
    ResponseEntity<String>hello2(){
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    @GetMapping("/main")
    ResponseEntity<String>main(){
        return new ResponseEntity<String>("main", HttpStatus.OK);
    }

    @GetMapping
    public Map<String,Object> hello(OAuth2AuthenticationToken oAuth2AuthenticationToken){
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }
}
