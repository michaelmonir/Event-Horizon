package com.EventHorizon.EventHorizon.Entities.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "buyed_ticket_collection")
@IdClass(BuyedTicketCollection.class)
@AllArgsConstructor
public class BuyedTicketCollection
{
    @Id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id")
    private SeatType seatType;

    @Column(nullable = false)
    int numberOfTickets;

    protected BuyedTicketCollection() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyedTicketCollection that = (BuyedTicketCollection) o;


        // not comparing dates as they are compared in different formats so will always return false
        return numberOfTickets == that.numberOfTickets
                && client.getId() == that.client.getId()
                && seatType.getId() == that.seatType.getId();
    }
}
