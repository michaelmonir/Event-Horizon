package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException
{
    public UserNotFoundException()
    {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "User not found";
    }
}
