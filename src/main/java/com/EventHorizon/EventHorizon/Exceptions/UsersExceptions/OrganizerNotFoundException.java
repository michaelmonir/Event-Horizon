package com.EventHorizon.EventHorizon.Exceptions.UsersExceptions;

import com.EventHorizon.EventHorizon.Exceptions.BaseException;

public class OrganizerNotFoundException extends BaseException {

    public OrganizerNotFoundException() {
        this.message = "Organizer Not Found ";
    }
}
