package com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions;

public class NotFinishedEventException extends WrongEventTypeException
{
    public NotFinishedEventException() {
        this.message = "Event is not finished yet";
    }
}
