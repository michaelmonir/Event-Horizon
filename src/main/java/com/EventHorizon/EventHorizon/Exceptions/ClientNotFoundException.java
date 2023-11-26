package com.EventHorizon.EventHorizon.Exceptions;

public class ClientNotFoundException extends SuperException {

    public ClientNotFoundException() {
        this.message = "Client Not Found ";
    }
}
