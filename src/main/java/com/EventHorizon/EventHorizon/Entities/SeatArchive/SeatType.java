package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;

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
    private Event event;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;

    protected SeatType(){}

    public SeatType(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatType seatType = (SeatType) o;
        return id == seatType.id
                && price == seatType.price
                && event.getId() == seatType.event.getId()
                && Objects.equals(name, seatType.name);
    }
}
