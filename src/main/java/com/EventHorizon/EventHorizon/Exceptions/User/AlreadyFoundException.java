package com.EventHorizon.EventHorizon.Exceptions.User;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class AlreadyFoundException extends BaseException {
    public AlreadyFoundException() {
        this.message = " Already Found ";
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
