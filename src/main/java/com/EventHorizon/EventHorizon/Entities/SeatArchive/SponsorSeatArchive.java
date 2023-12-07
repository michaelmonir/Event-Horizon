package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="sponsor_seat_archive")
@Getter
@Setter
@IdClass(SponsorArchiveCompositeKey.class)
public class SponsorSeatArchive
{
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id")
    private SeatType seatType;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sponsor_id", referencedColumnName = "id")
    private Sponsor sponsor;

    @Column(nullable = false)
    int total_number_of_seats;

    @Column(nullable = false)
    int available_number_of_seats;

    protected SponsorSeatArchive() { }

    public SponsorSeatArchive(SeatType seatType, Sponsor sponsor, int total_number_of_seats, int available_number_of_seats)
    {
        this.seatType = seatType;
        this.sponsor = sponsor;
        this.total_number_of_seats = total_number_of_seats;
        this.available_number_of_seats = available_number_of_seats;
    }
}
