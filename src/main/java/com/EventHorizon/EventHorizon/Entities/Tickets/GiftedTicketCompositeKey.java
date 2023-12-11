package com.EventHorizon.EventHorizon.Entities.Tickets;

import com.EventHorizon.EventHorizon.Entities.SeatArchive.SeatType;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Client;
import com.EventHorizon.EventHorizon.Entities.UserEntities.Sponsor;

public class GiftedTicketCompositeKey
{
    private Client client;
    private SeatType seatType;
    private Sponsor sponsor;
}
