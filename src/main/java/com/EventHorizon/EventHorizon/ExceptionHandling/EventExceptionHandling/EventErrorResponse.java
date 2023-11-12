package com.EventHorizon.EventHorizon.ExceptionHandling.EventExceptionHandling;

public class EventErrorResponse
{
    public int status;
    public String message;
    public long timestamp;

    public EventErrorResponse() {
    }
    public EventErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

}