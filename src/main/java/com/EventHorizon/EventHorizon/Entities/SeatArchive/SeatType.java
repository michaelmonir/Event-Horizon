package com.EventHorizon.EventHorizon.Entities.SeatArchive;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seat_type")
@Data
@NoArgsConstructor
public class SeatType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    public Event event;

    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public int price;

    public SeatType(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
