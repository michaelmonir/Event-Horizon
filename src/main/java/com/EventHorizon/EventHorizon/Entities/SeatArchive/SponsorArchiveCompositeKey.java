package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;

import java.io.Serializable;

public class SponsorArchiveCompositeKey implements Serializable
{
    private SeatType seatType;
    private Sponsor sponsor;
}
