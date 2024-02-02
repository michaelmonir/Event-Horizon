package com.EventHorizon.EventHorizon.Exceptions.User;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UserAccessDeniedException extends BaseException
{
    public UserAccessDeniedException()
    {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "Access Denied";
    }
}
