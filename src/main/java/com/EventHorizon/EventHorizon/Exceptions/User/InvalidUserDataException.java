package com.EventHorizon.EventHorizon.Exceptions.User;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidUserDataException extends BaseException {

    public InvalidUserDataException() {
        this.message = "Invalid user data provided";
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
