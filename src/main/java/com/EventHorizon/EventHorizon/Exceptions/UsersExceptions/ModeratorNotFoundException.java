package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ModeratorNotFoundException extends BaseException {
    public ModeratorNotFoundException() {
        this.message = "Moderator Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
