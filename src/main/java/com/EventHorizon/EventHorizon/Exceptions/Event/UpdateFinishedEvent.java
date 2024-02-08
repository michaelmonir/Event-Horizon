package com.EventHorizon.EventHorizon.Exceptions.Event;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UpdateFinishedEvent extends BaseException {

    public UpdateFinishedEvent() {
        this.message="Update Finished Event Is Forbidden";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
