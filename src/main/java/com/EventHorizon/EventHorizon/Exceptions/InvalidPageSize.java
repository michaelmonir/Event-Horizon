package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class InvalidPageSize extends ParentException{


    public InvalidPageSize() {
        this.message="Invalid Page Size";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
