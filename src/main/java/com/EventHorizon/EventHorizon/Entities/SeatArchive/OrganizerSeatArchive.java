package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name="organizer_seat_archive")
public class OrganizerSeatArchive
{
    @Id
    private Long seatTypeId;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id", nullable = false )
    private SeatType seatType;

//    @Column(nullable = false)
//    int total_number_of_seats;
//
//    @Column(nullable = false)
//    int available_number_of_seats;
}
