package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;

public class InformationNotFoundException extends BaseException {

    public InformationNotFoundException() {
        this.message = "Information Not Found ";
    }
}
