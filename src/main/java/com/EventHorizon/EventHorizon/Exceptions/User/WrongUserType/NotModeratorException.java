package com.EventHorizon.EventHorizon.Exceptions.User.WrongUserType;

import org.springframework.http.HttpStatus;

public class NotModeratorException extends WrongUserTypeException {
    public NotModeratorException() {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "User is not a moderator";
    }
}