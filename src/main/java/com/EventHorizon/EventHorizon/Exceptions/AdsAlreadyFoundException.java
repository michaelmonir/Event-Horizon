package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class AdsAlreadyFoundException extends BaseException{
    public AdsAlreadyFoundException() {
        this.message=" Ads Already Found";
        this.httpStatus= HttpStatus.CONFLICT;
    }
}
