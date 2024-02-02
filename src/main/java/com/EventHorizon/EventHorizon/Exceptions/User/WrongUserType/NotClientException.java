package com.EventHorizon.EventHorizon.Exceptions.User.WrongUserType;

import org.springframework.http.HttpStatus;

public class NotClientException extends WrongUserTypeException {
    public NotClientException() {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "User is not a client";
    }
}
