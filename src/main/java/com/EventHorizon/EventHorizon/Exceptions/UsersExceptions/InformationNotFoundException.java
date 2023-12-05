package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InformationNotFoundException extends BaseException {

    public InformationNotFoundException() {
        this.message = "Information Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
