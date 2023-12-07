package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NewEventDateIsBeforeNow extends BaseException {
    public NewEventDateIsBeforeNow() {
        this.message="New Event Date Is Before Now";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
