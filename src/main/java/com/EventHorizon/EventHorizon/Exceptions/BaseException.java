package com.EventHorizon.EventHorizon.Exceptions;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException
{
    public HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public String message = "Message not set for this class yet";
}
