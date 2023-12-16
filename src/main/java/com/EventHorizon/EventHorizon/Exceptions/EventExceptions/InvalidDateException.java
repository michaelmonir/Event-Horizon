package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidDateException extends BaseException {

    public InvalidDateException() {
        this.message="Invalid Date";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
