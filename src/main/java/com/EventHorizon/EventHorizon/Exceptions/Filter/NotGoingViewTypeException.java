package com.EventHorizon.EventHorizon.Exceptions.Filter;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NotGoingViewTypeException extends BaseException {
    public NotGoingViewTypeException() {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = "Not going view type exception";
    }
}
