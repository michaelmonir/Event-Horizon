package com.EventHorizon.EventHorizon.Exceptions.User.WrongUserType;

import org.springframework.http.HttpStatus;

public class NotSponsorException extends WrongUserTypeException {
    public NotSponsorException() {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "User is not a sponsor";
    }
}
