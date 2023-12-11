package com.EventHorizon.EventHorizon.Exceptions.Tickets;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class BuyedTicketCollectionNotFoundException extends BaseException
{
    public BuyedTicketCollectionNotFoundException() {
        this.message="Buyed Ticket Collection Not Found Exception";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
