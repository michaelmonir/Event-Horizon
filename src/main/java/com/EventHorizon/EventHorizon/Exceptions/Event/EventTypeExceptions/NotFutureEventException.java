package com.EventHorizon.EventHorizon.Exceptions.Event.EventTypeExceptions;

public class NotFutureEventException extends WrongEventTypeException
{
    public NotFutureEventException() {
        this.message="Not Future Event Exception";
    }
}
