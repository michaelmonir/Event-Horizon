package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class OrganizerNotFoundException extends BaseException {

    public OrganizerNotFoundException() {
        this.message = "Organizer Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
