package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;

public class ModeratorNotFoundException extends BaseException {
    public ModeratorNotFoundException() {
        this.message = "Moderator Not Found ";
    }
}
