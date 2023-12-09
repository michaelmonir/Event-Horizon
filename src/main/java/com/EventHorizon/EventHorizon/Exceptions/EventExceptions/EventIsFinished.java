package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class EventIsFinished extends BaseException {
    public EventIsFinished() {
        this.message="Event Is Finished";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
