package com.EventHorizon.EventHorizon.security.authenticationMessages;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VerifyRequest {
    private String token;
    private String verifyCode;
}
