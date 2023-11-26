package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;


public class EventNotFoundException extends BaseException {

    public EventNotFoundException() {
        this.message="Event Not Found";
        this.httpStatus= HttpStatus.NOT_FOUND;
    }
}
