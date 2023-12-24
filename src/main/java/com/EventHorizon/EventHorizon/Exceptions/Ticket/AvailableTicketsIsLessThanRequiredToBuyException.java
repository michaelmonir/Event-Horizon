package com.EventHorizon.EventHorizon.Exceptions.Ticket;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class AvailableTicketsIsLessThanRequiredToBuyException extends BaseException {
    public AvailableTicketsIsLessThanRequiredToBuyException() {
        this.message="Available Tickets Is Less Than Required";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
