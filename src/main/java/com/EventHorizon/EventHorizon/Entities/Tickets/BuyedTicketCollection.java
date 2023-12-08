package com.EventHorizon.EventHorizon.Entities.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.Date;

@Entity
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
    Date buyingTime;

    @Column(nullable = false)
    int numberOfTickets;

    protected BuyedTicketCollection() {}
}
