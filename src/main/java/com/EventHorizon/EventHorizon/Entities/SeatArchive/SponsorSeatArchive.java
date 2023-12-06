package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import jakarta.persistence.*;

@Entity
@Table(name="sponsor_seat_archive")
public class SponsorSeatArchive
{
    @EmbeddedId
    private SponsorArchiveCompositeKey sponsorArchiveCompositeKey;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("seatTypeId")
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id")
    private SeatType seatType;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("sponsorId")
    @JoinColumn(name = "sponsor_id", referencedColumnName = "id")
    private Sponsor sponsor;

    @Column(nullable = false)
    int total_number_of_seats;

    @Column(nullable = false)
    int available_number_of_seats;
}
