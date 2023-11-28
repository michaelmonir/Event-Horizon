package com.EventHorizon.EventHorizon.security.execptions;

public class ExistingUserName extends RuntimeException{
    public ExistingUserName(String message) {
        super(message);
    }


}