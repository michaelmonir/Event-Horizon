package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class WrongEventIdException extends BaseException
{
    public WrongEventIdException()
    {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "Wrong event id";
    }
}
