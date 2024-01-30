package com.EventHorizon.EventHorizon.Entities.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;

import java.io.Serializable;

public class BuyedTicketCompositeKey implements Serializable {
    private Client client;
    private SeatType seatType;
}
