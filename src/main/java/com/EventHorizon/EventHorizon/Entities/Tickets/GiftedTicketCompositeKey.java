package com.EventHorizon.EventHorizon.Entities.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Client;
import com.EventHorizon.EventHorizon.Entities.UpdateUsers.Sponsor;

import java.io.Serializable;

public class GiftedTicketCompositeKey implements Serializable
{
    private Client client;
    private SeatType seatType;
    private Sponsor sponsor;
}
