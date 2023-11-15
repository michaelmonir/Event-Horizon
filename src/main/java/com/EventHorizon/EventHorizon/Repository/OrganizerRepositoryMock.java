package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;
import com.EventHorizon.EventHorizon.Users.Organizer.OrganizerMock;

public class OrganizerRepositoryMock implements OrganizerRepository
{
    public Organizable findById(int organizerId){
        return new OrganizerMock();
    }
}
