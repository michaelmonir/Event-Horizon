package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class EventNotFoundException extends BaseException
{
    public EventNotFoundException()
    {
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.message = "Event Not Found";
    }
}
