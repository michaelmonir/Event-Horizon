package com.EventHorizon.EventHorizon.Exceptions.User.WrongUserType;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class WrongUserTypeException extends BaseException {
    public WrongUserTypeException() {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "Wrong user type";
    }
}
