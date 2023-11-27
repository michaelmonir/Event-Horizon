package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;

public class AlreadyFoundException extends BaseException {
    public AlreadyFoundException() {
        this.message = " Already Found ";
    }
}
