package com.EventHorizon.EventHorizon.Exceptions.SeatArchive;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class OrganizerSeatArchiveShouldHaveIdWhileCreatingException extends BaseException
{
    public OrganizerSeatArchiveShouldHaveIdWhileCreatingException() {
        this.message="Organizer Seat Archive Should Have Id While Creating Exception";
        this.httpStatus= HttpStatus.BAD_REQUEST;
    }
}
