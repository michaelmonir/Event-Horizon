package com.EventHorizon.EventHorizon.Entities;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.Tickets.BuyedTicketCompositeKey;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import jakarta.persistence.*;
import lombok.Data;
import net.jcip.annotations.Immutable;

@Data
@Entity(name = "client_going_view")
@Immutable
@IdClass(ClientGoingViewCompositeId.class)
public class ClientGoingView {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;
}
