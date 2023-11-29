package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ClientNotFoundException extends BaseException {

    public ClientNotFoundException() {
        this.message = "Client Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
