package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class InvalidPageIndex extends BaseException{
    public InvalidPageIndex() {
        this.message="Invalid Page Index";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
