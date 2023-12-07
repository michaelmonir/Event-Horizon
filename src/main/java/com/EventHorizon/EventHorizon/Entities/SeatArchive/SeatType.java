package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "seat_type")
@Data
@ToString
public class SeatType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    public Event event;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;

    @OneToOne(mappedBy = "seatType")
    private OrganizerSeatArchive organizerSeatArchive;

    protected SeatType(){}

    public SeatType(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
