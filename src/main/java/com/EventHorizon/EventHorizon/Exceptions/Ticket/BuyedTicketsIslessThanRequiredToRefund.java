package com.EventHorizon.EventHorizon.Exceptions.Ticket;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class BuyedTicketsIslessThanRequiredToRefund extends BaseException {
    public BuyedTicketsIslessThanRequiredToRefund() {
        this.message="Buyed Tickets Is less Than Required To Refund";
        this.httpStatus= HttpStatus.FORBIDDEN;
    }
}
