package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="organizer_seat_archive")
@Data
@ToString
public class OrganizerSeatArchive
{
    @Id
    private int seatTypeId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("seatTypeId")
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id")
    private SeatType seatType;

    @Column(nullable = false)
    int total_number_of_seats;

    @Column(nullable = false)
    int available_number_of_seats;

    protected OrganizerSeatArchive(){}

    public OrganizerSeatArchive(SeatType seatType, int total_number_of_seats, int available_number_of_seats) {
        this.seatTypeId = seatType.getId();
        this.total_number_of_seats = total_number_of_seats;
        this.available_number_of_seats = available_number_of_seats;
    }
}
