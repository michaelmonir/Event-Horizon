package com.EventHorizon.EventHorizon.Exceptions.User.WrongUserType;

import org.springframework.http.HttpStatus;

public class NotAdminException extends WrongUserTypeException {
    public NotAdminException() {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "User is not an admin";
    }
}
