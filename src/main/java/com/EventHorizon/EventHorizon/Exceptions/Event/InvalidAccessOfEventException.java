package com.EventHorizon.EventHorizon.Exceptions.Event;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidAccessOfEventException extends BaseException {
    public InvalidAccessOfEventException() {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "Invalid access of event";
    }
}
