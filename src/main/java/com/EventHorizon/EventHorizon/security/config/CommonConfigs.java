package com.EventHorizon.EventHorizon.security.config;

import lombok.Data;

@Data
public class CommonConfigs {
    static final String[] AUTH_WHITELIST = {
            "/proxy/**",
            "/v1/main",
//            "/oauth2/authorization/google"
    };

}
