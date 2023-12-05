package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends BaseException {

    public RoleNotFoundException() {
        this.message = "Role Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
