package com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public abstract class WrongEventTypeException extends BaseException
{
    public WrongEventTypeException() {
        this.message="Wrong Event Type Exception";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
