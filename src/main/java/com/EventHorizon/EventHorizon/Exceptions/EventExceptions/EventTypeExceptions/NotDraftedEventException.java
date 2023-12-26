package com.EventHorizon.EventHorizon.Exceptions.EventExceptions.EventTypeExceptions;

public class NotDraftedEventException extends WrongEventTypeException{

    public NotDraftedEventException() {
        this.message = "Event is not a Drafted Event";
    }
}
