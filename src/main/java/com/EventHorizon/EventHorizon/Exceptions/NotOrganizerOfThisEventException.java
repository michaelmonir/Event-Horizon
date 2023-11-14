package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class NotOrganizerOfThisEventException extends BaseException
{
    public NotOrganizerOfThisEventException()
    {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "Not organizer of this event";
    }
}
