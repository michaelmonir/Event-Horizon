package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class EventAlreadyExisting extends BaseException{
    public EventAlreadyExisting() {
        this.message="Event Already Existing";
        this.httpStatus= HttpStatus.CONFLICT;
    }
}
