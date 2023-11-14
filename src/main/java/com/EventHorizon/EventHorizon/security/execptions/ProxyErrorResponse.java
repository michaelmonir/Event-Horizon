package com.EventHorizon.EventHorizon.security.execptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProxyErrorResponse {

    private int status;
    private String message;
    private long timeStamp;
}
