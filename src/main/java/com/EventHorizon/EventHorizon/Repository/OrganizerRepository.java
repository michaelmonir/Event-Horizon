package com.EventHorizon.EventHorizon.Repository;

import com.EventHorizon.EventHorizon.Users.Organizer.Organizable;

public interface OrganizerRepository
{
    public Organizable findById(int organizerId);
}
