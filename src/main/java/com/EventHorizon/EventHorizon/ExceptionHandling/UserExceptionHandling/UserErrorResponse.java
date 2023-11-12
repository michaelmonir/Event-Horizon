package com.EventHorizon.EventHorizon.ExceptionHandling.UserExceptionHandling;


public class UserErrorResponse
{
    public int status;
    public String message;
    public long timestamp;

    public UserErrorResponse() {
    }
    public UserErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

}
