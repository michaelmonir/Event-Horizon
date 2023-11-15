package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class AdsNotFoundException extends BaseException{
    public AdsNotFoundException() {
        this.message="Ads Not Found";
        this.httpStatus= HttpStatus.NOT_FOUND;
    }
}
