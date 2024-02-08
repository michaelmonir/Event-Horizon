package com.EventHorizon.EventHorizon.Exceptions.Event.EventTypeExceptions;

public class NotFinishedEventException extends WrongEventTypeException
{
    public NotFinishedEventException() {
        this.message = "Event is not finished yet";
    }
}
