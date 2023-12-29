package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidEventIdException extends BaseException
{
    public InvalidEventIdException() {
        this.message = "Invalid Event Id";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
