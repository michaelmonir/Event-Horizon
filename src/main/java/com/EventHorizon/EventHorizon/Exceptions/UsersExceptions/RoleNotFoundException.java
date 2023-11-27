package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;

public class RoleNotFoundException extends BaseException {

    public RoleNotFoundException() {
        this.message = "Role Not Found ";
    }
}
