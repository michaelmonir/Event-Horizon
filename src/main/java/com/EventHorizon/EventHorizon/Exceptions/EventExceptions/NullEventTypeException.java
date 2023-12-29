package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NullEventTypeException extends BaseException
{
    public NullEventTypeException() {
        this.message = "Event type is null";
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
