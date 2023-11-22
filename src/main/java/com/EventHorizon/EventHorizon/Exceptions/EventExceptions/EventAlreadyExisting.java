package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class EventAlreadyExisting extends BaseException {
    public EventAlreadyExisting() {
        this.message="Event Already Existing";
        this.httpStatus= HttpStatus.CONFLICT;
    }
}
