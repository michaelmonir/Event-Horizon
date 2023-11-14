package com.EventHorizon.EventHorizon.security.execptions;

public class ExistingUserName extends RuntimeException{
    public ExistingUserName(String message) {
        super(message);
    }

    public ExistingUserName(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingUserName(Throwable cause) {
        super(cause);
    }
    public ExistingUserName(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}