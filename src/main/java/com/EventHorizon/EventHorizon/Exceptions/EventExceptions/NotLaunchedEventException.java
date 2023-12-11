package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NotLaunchedEventException extends BaseException
{
    public NotLaunchedEventException()
    {
        this.message = "Not Lauched Event Exception";
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
