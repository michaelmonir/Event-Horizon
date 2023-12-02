package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class UserNotAnOrganizerException extends BaseException {

    public UserNotAnOrganizerException()
    {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "User not an Organizer";
    }
}
