package com.EventHorizon.EventHorizon.Exceptions.EventExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UpdatePastEvent extends BaseException {

    public UpdatePastEvent() {
        this.message="Update Past Event Is Forbidden";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
