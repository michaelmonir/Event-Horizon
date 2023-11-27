package com.EventHorizon.EventHorizon.security;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JWTServiceTest {
    @Autowired
    JwtService jwtService;

    @Test
    public void testTokenUserName(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ2ZXJpZnlDb2RlIjoiMjI0NTM1Iiwic3ViIjoiYWhtZWRtb2hhbWVkMTRAZ21haWwuY29tIiwiaWF0IjoxNzAwOTk5MTYyLCJleHAiOjEwMDE3MDA5OTkxNjJ9.azPfzNnM1YbefcC6127ZNtEhI7wOsSAANrenI_NMYu8";
        assertEquals(jwtService.extractUserName(token),"ahmedmohamed14@gmail.com");
    }
    @Test
    public void testTokenUserName2(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ2ZXJpZnlDb2RlIjoiNDIwODg1Iiwic3ViIjoiYWhtZWRtb2hhbWVkMTVAZ21haWwuY29tIiwiaWF0IjoxNzAwOTk5MjI3LCJleHAiOjEwMDE3MDA5OTkyMjd9.ExJGNsWqmvIvp0Zwr1dbFCx48RaPJk8FpQAHMX9yXO4";
        assertEquals(jwtService.extractUserName(token),"ahmedmohamed15@gmail.com");
    }

    @Test
    public void testTokenIssuedAtDate(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ2ZXJpZnlDb2RlIjoiMjI0NTM1Iiwic3ViIjoiYWhtZWRtb2hhbWVkMTRAZ21haWwuY29tIiwiaWF0IjoxNzAwOTk5MTYyLCJleHAiOjEwMDE3MDA5OTkxNjJ9.azPfzNnM1YbefcC6127ZNtEhI7wOsSAANrenI_NMYu8";
        System.out.println(jwtService.extractClaim(token, Claims::getIssuedAt).getTime());
        assertEquals(jwtService.extractClaim(token, Claims::getIssuedAt),new Date(1700999162000l));
    }
    @Test
    public void testTokenIssuedAtDate2(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ2ZXJpZnlDb2RlIjoiNDIwODg1Iiwic3ViIjoiYWhtZWRtb2hhbWVkMTVAZ21haWwuY29tIiwiaWF0IjoxNzAwOTk5MjI3LCJleHAiOjEwMDE3MDA5OTkyMjd9.ExJGNsWqmvIvp0Zwr1dbFCx48RaPJk8FpQAHMX9yXO4";

        assertEquals(jwtService.extractClaim(token, Claims::getIssuedAt),new Date(1700999227000l));
    }
    @Test
    public void testTokenExpirationDate(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ2ZXJpZnlDb2RlIjoiMjI0NTM1Iiwic3ViIjoiYWhtZWRtb2hhbWVkMTRAZ21haWwuY29tIiwiaWF0IjoxNzAwOTk5MTYyLCJleHAiOjEwMDE3MDA5OTkxNjJ9.azPfzNnM1YbefcC6127ZNtEhI7wOsSAANrenI_NMYu8";

        assertEquals(jwtService.extractClaim(token, Claims::getExpiration),new Date(1001700999162000l));
    }
    @Test
    public void testTokenExpirationDate2(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJ2ZXJpZnlDb2RlIjoiNDIwODg1Iiwic3ViIjoiYWhtZWRtb2hhbWVkMTVAZ21haWwuY29tIiwiaWF0IjoxNzAwOTk5MjI3LCJleHAiOjEwMDE3MDA5OTkyMjd9.ExJGNsWqmvIvp0Zwr1dbFCx48RaPJk8FpQAHMX9yXO4";

        assertEquals(jwtService.extractClaim(token, Claims::getExpiration),new Date(1001700999227000l));
    }

}
