package com.EventHorizon.EventHorizon.Exceptions.User;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NotAdminOperationException extends BaseException {

    public NotAdminOperationException() {
        this.message = "This Operation Is Not Allowed For This User Type";
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
