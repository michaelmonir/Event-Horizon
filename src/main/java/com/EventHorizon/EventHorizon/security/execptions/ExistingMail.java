package com.EventHorizon.EventHorizon.security.execptions;

public class ExistingMail extends RuntimeException{
    public ExistingMail(String message) {
        super(message);
    }

    public ExistingMail(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistingMail(Throwable cause) {
        super(cause);
    }
    public ExistingMail(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}