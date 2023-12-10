package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidateException extends BaseException {
    public InvalidateException() {
        this.message="New Event Date Is Before Now";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
