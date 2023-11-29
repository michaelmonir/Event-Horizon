package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class SponsorNotFoundException extends BaseException {

    public SponsorNotFoundException() {
            this.message="Sponsor Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
