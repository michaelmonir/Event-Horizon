package com.EventHorizon.EventHorizon.EventApis;

import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;
import com.EventHorizon.EventHorizon.Users.Organizer.OrganizerMock;

public class OrganizerRepositoryMock implements OrganizerRepository
{
    public Organizable findById(){
        return new OrganizerMock();
    }
}
