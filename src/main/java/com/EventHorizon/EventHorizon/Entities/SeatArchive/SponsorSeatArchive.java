package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Sponsor;
import jakarta.persistence.*;
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
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id")
    private SeatType seatType;

    @Id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SponsorSeatArchive that = (SponsorSeatArchive) o;
        return total_number_of_seats == that.total_number_of_seats
                && available_number_of_seats == that.available_number_of_seats
                && seatType.getId() ==  that.seatType.getId()
                && sponsor.getId() == that.sponsor.getId();
    }
}
