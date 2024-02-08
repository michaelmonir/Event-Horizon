package com.EventHorizon.EventHorizon.Exceptions.Event.EventTypeExceptions;

public class NotDraftedEventException extends WrongEventTypeException{

    public NotDraftedEventException() {
        this.message = "Event is not a Drafted Event";
    }
}
