package com.EventHorizon.EventHorizon.Exceptions.Event;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidEventDataException extends BaseException {
    public InvalidEventDataException() {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = "INVALID EVENT DATA";
    }
}
