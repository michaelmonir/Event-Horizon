package com.EventHorizon.EventHorizon.Exceptions.Event;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class EventIsFinished extends BaseException {
    public EventIsFinished() {
        this.message="Event Is Finished";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
