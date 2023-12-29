package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class AdminNotFoundException extends BaseException {

    public AdminNotFoundException() {
        this.message = "Admin Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}