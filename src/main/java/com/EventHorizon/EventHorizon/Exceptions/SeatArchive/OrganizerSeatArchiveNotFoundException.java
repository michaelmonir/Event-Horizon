package com.EventHorizon.EventHorizon.Exceptions.SeatArchive;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class OrganizerSeatArchiveNotFoundException extends BaseException
{
    public OrganizerSeatArchiveNotFoundException() {
        this.message="Organizer Seat Archive Not Found Exception";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
