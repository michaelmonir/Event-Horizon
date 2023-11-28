package com.EventHorizon.EventHorizon.security.execptions;

public class ExistingMail extends RuntimeException{
    public ExistingMail(String message) {
        super(message);
    }

}