package com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions;

public class NotFutureEventException extends WrongEventTypeException
{
    public NotFutureEventException() {
        this.message="Not Future Event Exception";
    }
}
