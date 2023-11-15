package com.EventHorizon.EventHorizon.ExceptionHandling;

public class ErrorResponse
{
    public int status;
    public String message;
    public long timestamp;

    public ErrorResponse() {
    }
    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

}