package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;

public class ClientNotFoundException extends BaseException {

    public ClientNotFoundException() {
        this.message = "Client Not Found ";
    }
}
