package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class NotModeratorOperationsException extends BaseException {

    public NotModeratorOperationsException() {
        this.message = "Moderator try to Delete Moderator  Error";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

}
