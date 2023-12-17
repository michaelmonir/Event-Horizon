package com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions;

public class NotLaunchedEventException extends WrongEventTypeException
{
    public NotLaunchedEventException() {
        this.message="Not Launched Event Exception";
    }
}
