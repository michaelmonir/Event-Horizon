package com.EventHorizon.EventHorizon.security.authenticationMessages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyRequest {
    private String token;
    private String verifyCode;
}
