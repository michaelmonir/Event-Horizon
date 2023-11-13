package com.EventHorizon.EventHorizon.security;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JWTServiceTest {
    @Test
    public void testTokenUserName(){
        JwtService jwtService = new JwtService();
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZG1vaGFtZWQxNEBnbWFpbC5jb20iLCJpYXQiOjE2OTk4OTcxNjUsImV4cCI6MTcwMDg5NzE2NX0.lg03uCb570KRY8fszSQyaOMql82mbJYgCQAyZTpr87s";
        assertEquals(jwtService.extractUserName(token),"ahmedmohamed14@gmail.com");
    }
    @Test
    public void testTokenUserName2(){
        JwtService jwtService = new JwtService();
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZEBnbWFpbC5jb20iLCJpYXQiOjE2OTk4OTY4NTMsImV4cCI6MTcwMDg5Njg1M30.TtS--nhZ9iAE75HspP9f1-7eMfgxajufuRwjbyZC-7c";
        assertEquals(jwtService.extractUserName(token),"ahmed@gmail.com");
    }

    @Test
    public void testTokenIssuedAtDate(){
        JwtService jwtService = new JwtService();
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZG1vaGFtZWQxNEBnbWFpbC5jb20iLCJpYXQiOjE2OTk4OTcxNjUsImV4cCI6MTcwMDg5NzE2NX0.lg03uCb570KRY8fszSQyaOMql82mbJYgCQAyZTpr87s";
        assertEquals(jwtService.extractClaim(token, Claims::getIssuedAt),new Date(1699897165000l));
    }
    @Test
    public void testTokenIssuedAtDate2(){
        JwtService jwtService = new JwtService();
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZEBnbWFpbC5jb20iLCJpYXQiOjE2OTk4OTY4NTMsImV4cCI6MTcwMDg5Njg1M30.TtS--nhZ9iAE75HspP9f1-7eMfgxajufuRwjbyZC-7c";
        assertEquals(jwtService.extractClaim(token, Claims::getIssuedAt),new Date(1699896853000l));
    }
    @Test
    public void testTokenExpirationDate(){
        JwtService jwtService = new JwtService();
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZG1vaGFtZWQxNEBnbWFpbC5jb20iLCJpYXQiOjE2OTk4OTcxNjUsImV4cCI6MTcwMDg5NzE2NX0.lg03uCb570KRY8fszSQyaOMql82mbJYgCQAyZTpr87s";
        assertEquals(jwtService.extractClaim(token, Claims::getExpiration),new Date(1700897165000l));
    }
    @Test
    public void testTokenExpirationDate2(){
        JwtService jwtService = new JwtService();
        String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaG1lZEBnbWFpbC5jb20iLCJpYXQiOjE2OTk4OTY4NTMsImV4cCI6MTcwMDg5Njg1M30.TtS--nhZ9iAE75HspP9f1-7eMfgxajufuRwjbyZC-7c";
        assertEquals(jwtService.extractClaim(token, Claims::getExpiration),new Date(1700896853000l));
    }

}
