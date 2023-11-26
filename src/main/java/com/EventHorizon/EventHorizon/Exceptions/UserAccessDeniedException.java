package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class UserAccessDeniedException extends BaseException
{
    public UserAccessDeniedException()
    {
        this.httpStatus = HttpStatus.FORBIDDEN;
        this.message = "Access Denied";
    }
}
