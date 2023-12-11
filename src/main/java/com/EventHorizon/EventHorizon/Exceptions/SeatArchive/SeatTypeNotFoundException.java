package com.EventHorizon.EventHorizon.Exceptions.SeatArchive;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class SeatTypeNotFoundException extends BaseException
{
    public SeatTypeNotFoundException() {
        this.message="Seat Type Not Found Exception";
        this.httpStatus= HttpStatus.NOT_FOUND;
    }
}
