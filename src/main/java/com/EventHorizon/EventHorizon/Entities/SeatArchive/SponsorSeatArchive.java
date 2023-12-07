package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="sponsor_seat_archive")
@Data
public class SponsorSeatArchive
{
    @EmbeddedId
    private SponsorArchiveCompositeKey id;

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

    public SponsorSeatArchive(SeatType seatType, Sponsor sponsor, int total_number_of_seats, int available_number_of_seats)
    {
        SponsorArchiveCompositeKey id = new SponsorArchiveCompositeKey(seatType.getId(), sponsor.getId());
        this.id = id;
        this.total_number_of_seats = total_number_of_seats;
        this.available_number_of_seats = available_number_of_seats;
    }
}
