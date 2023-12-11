package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "seat_type")
@Data
@ToString
@EqualsAndHashCode
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
}
