package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class EventAlreadyExisting extends BaseException
{
    public EventAlreadyExisting()
    {
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.message = "Event Already Existing";
    }
}
