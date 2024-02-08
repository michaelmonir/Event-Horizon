package com.EventHorizon.EventHorizon.Exceptions.Event.EventTypeExceptions;

public class NotLaunchedEventException extends WrongEventTypeException
{
    public NotLaunchedEventException() {
        this.message="Not Launched Event Exception";
    }
}
