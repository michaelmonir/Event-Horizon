package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class InvalidPageIndex extends ParentException{
    public InvalidPageIndex() {
        this.message="Invalid Page Index";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
