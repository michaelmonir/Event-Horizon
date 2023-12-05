package com.EventHorizon.EventHorizon.Services;

import com.EventHorizon.EventHorizon.security.Service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenInformationService {

    @Autowired
    private JwtService jwtService;

    public int getUserIdFromToken(HttpServletRequest request)
    {
        final String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        return jwtService.extractId(token);
    }
}
