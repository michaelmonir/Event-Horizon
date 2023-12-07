package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class SponsorArchiveCompositeKey implements Serializable
{
    @Column(name = "seat_type_id")
    private int seatTypeId;
    @Column(name = "sponsor_id")
    private int sponsorId;

    protected SponsorArchiveCompositeKey() { }

    public SponsorArchiveCompositeKey(int seatTypeId, int sponsorId)
    {
        this.seatTypeId = seatTypeId;
        this.sponsorId = sponsorId;
    }
}
