package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class EventIsAlreadyLaunched extends BaseException {
    public EventIsAlreadyLaunched() {
        this.message="Event Is Already Launched";
        this.httpStatus= HttpStatus.NOT_FOUND;
    }
}
