package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class WrongEventIdException extends BaseException
{
    public WrongEventIdException()
    {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "Wrong event id";
    }
}
