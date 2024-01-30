package com.EventHorizon.EventHorizon.Entities.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Sponsor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "gifted_ticket_collection")
@IdClass(GiftedTicketCompositeKey.class)
@AllArgsConstructor
public class GiftedTicketCollection
{
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "seat_type_id", referencedColumnName = "id")
    private SeatType seatType;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sponsor_id", referencedColumnName = "id")
    private Sponsor sponsor;

    @Column(nullable = false)
    int numberOfTickets;

    protected GiftedTicketCollection() {}
}
